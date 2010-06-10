package de.cosmocode.palava.ipc.conversation;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

import de.cosmocode.palava.scope.AbstractScopeContext;

/**
 * Abstract {@link Conversation} implementation.
 *
 * @since 1.4
 * @author Willi Schoenborn
 */
abstract class AbstractConversation extends AbstractScopeContext implements Conversation {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractConversation.class);
    
    private Map<Object, Object> context;
    
    @Override
    protected Map<Object, Object> context() {
        if (context == null) {
            context = Maps.newHashMap();
        }
        return context;
    }

    @Override
    public final void destroy() {
        clear();
    }

    @Override
    public void end() throws CompletionFailedException {
        final Map<Throwable, Object> errors = Maps.newHashMap();
        final Iterator<Entry<Object, Object>> iterator = iterator();
        
        while (iterator.hasNext()) {
            final Entry<Object, Object> entry = iterator.next();

            if (entry.getKey() instanceof Completable) {
                try {
                    LOG.trace("Completing key {}", entry.getKey());
                    Completable.class.cast(entry.getKey()).complete();
                    /*CHECKSTYLE:OFF*/
                } catch (RuntimeException e) {
                    /*CHECKSTYLE:ON*/
                    LOG.error("Failed to complete key: " + entry.getKey(), e);
                    errors.put(e, entry.getKey());
                }
            }
            
            if (entry.getValue() instanceof Completable) {
                try {
                    LOG.trace("Completing value {}", entry.getValue());
                    Completable.class.cast(entry.getValue()).complete();
                    /*CHECKSTYLE:OFF*/
                } catch (RuntimeException e) {
                    /*CHECKSTYLE:ON*/
                    LOG.error("Failed to complete value: " + entry.getValue(), e);
                    errors.put(e, entry.getValue());
                }
            }
        }
        
        if (errors.size() > 0) {
            throw new CompletionFailedException(errors);
        }
    }

}

package de.cosmocode.palava.ipc.conversation;

import java.util.Collections;
import java.util.Map;

import com.google.common.base.Functions;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import de.cosmocode.commons.Throwables;

/**
 * An exception containing execptions and their source objects.
 *
 * @since 1.4
 * @author Willi Schoenborn
 */
public final class CompletionFailedException extends Exception {

    private static final long serialVersionUID = -5181069031934071159L;
    
    private static final Joiner JOINER = Joiner.on("\n");
    
    private final Map<Throwable, Object> errors = Maps.newHashMap();
    
    public CompletionFailedException() {
        super();
    }

    public CompletionFailedException(Map<Throwable, Object> errors) {
        this.errors.putAll(errors);
    }

    /**
     * Adds the specified pair to this exception.
     * 
     * @since 1.4
     * @param throwable the occured throwable
     * @param object the object which has thrown the corresponding exception
     */
    void put(Throwable throwable, Object object) {
        errors.put(throwable, object);
    }
    
    public Map<Throwable, Object> getErrors() {
        return Collections.unmodifiableMap(errors);
    }
    
    @Override
    public String getMessage() {
        return JOINER.join(Iterables.transform(errors.keySet(), Throwables.getMessage()));
    }
    
    @Override
    public String toString() {
        return String.format("%s:\n%s", 
            this, JOINER.join(Iterables.transform(errors.keySet(), Functions.toStringFunction()))
        );
    }
    
}

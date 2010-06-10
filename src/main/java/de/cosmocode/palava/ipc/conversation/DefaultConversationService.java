package de.cosmocode.palava.ipc.conversation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.name.Names;

import de.cosmocode.palava.ipc.IpcSession;

/**
 * Default {@link ConversationService} implementation.
 *
 * @since 1.4
 * @author Willi Schoenborn
 */
final class DefaultConversationService implements ConversationService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultConversationService.class);

    private final Provider<IpcSession> currentSession;
    
    @Inject
    public DefaultConversationService(Provider<IpcSession> currentSession) {
        this.currentSession = Preconditions.checkNotNull(currentSession, "CurrentSession");
    }

    @Override
    public Conversation get(String name) {
        final String key = Key.get(Conversation.class, Names.named(name)).toString();
        final IpcSession session = currentSession.get();
        final Conversation present = session.get(key);
        if (present == null) {
            final Conversation conversation = new AbstractConversation() {
                
                @Override
                public void abort() {
                    try {
                        clear();
                    } finally {
                        session.remove(key);
                    }
                }
                
            };
            LOG.trace("Starting new conversation {}", conversation);
            session.set(key, conversation);
            return conversation;
        } else {
            LOG.trace("Found old conversation {} in session", present);
            return present;
        }
    }

}

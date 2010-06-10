package de.cosmocode.palava.ipc.conversation;

/**
 * A Service which allows creation and retrieval of {@link Conversation}s.
 *
 * @since 1.4
 * @author Willi Schoenborn
 */
public interface ConversationService {

    /**
     * Retrieves a named Conversation. This may result in
     * the creation of a conversation if no conversation
     * with the specified name is currently in progress.
     * 
     * @since 1.4
     * @param name the name of the conversation
     * @return the conversation associated with the given name
     */
    Conversation get(String name);
    
}

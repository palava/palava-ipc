package de.cosmocode.palava.ipc.conversation;

/**
 * A callback interface which will be notified upon {@link Conversation#end()}.
 *
 * @since 1.4
 * @author Willi Schoenborn
 */
public interface Completable {

    /**
     * Callback method.
     * 
     * @since 1.4
     */
    void complete();
    
}

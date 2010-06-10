package de.cosmocode.palava.ipc.conversation;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Singleton;

/**
 * Binds {@link ConversationService} to {@link DefaultConversationService}.
 *
 * @since 1.0
 * @author Willi Schoenborn
 */
public final class DefaultConversationServiceModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(ConversationService.class).to(DefaultConversationService.class).in(Singleton.class);
    }

}

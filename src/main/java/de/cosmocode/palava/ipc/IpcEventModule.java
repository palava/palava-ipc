package de.cosmocode.palava.ipc;

import de.cosmocode.palava.core.Registry.Proxy;
import de.cosmocode.palava.core.Registry.SilentProxy;
import de.cosmocode.palava.core.inject.ProxyModule;

/**
 * Module which binds all event interfaces of this project
 * as {@link Proxy}/{@link SilentProxy}s.
 *
 * @since 1.3
 * @author Willi Schoenborn
 */
public final class IpcEventModule extends ProxyModule {

    @Override
    protected void configure() {
        // proxies
        bindProxy(IpcCallCreateEvent.class);
        bindProxy(IpcCallDestroyEvent.class);
        bindProxy(IpcConnectionCreateEvent.class);
        bindProxy(IpcConnectionDestroyEvent.class);
        bindProxy(IpcSessionCreateEvent.class);
        bindProxy(IpcSessionSuspendEvent.class);
        bindProxy(IpcSessionResumeEvent.class);
        bindProxy(IpcSessionDestroyEvent.class);
        
        // silent proxies
        bindSilentProxy(IpcCallCreateEvent.class);
        bindSilentProxy(IpcCallDestroyEvent.class);
        bindSilentProxy(IpcConnectionCreateEvent.class);
        bindSilentProxy(IpcConnectionDestroyEvent.class);
        bindSilentProxy(IpcSessionCreateEvent.class);
        bindSilentProxy(IpcSessionSuspendEvent.class);
        bindSilentProxy(IpcSessionResumeEvent.class);
        bindSilentProxy(IpcSessionDestroyEvent.class);
    }

}

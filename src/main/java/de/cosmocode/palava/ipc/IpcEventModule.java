/**
 * Copyright 2010 CosmoCode GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

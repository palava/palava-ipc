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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;

import de.cosmocode.palava.core.Registry;
import de.cosmocode.palava.core.Service;

/**
 * Registers for all scope/context events and logs them using trace.
 * 
 * @author Tobias Sarnowski
 * @author Willi Schoenborn
 */
public final class EventLogger implements Service,
    IpcConnectionCreateEvent, IpcConnectionDestroyEvent,
    IpcSessionCreateEvent, IpcSessionDestroyEvent,
    IpcCallCreateEvent, IpcCallDestroyEvent {
    
    private static final Logger LOG = LoggerFactory.getLogger(EventLogger.class);

    @Inject
    public EventLogger(Registry registry) {
        Preconditions.checkNotNull(registry, "Registry");
        registry.register(IpcSessionCreateEvent.class, this);
        registry.register(IpcSessionDestroyEvent.class, this);
        registry.register(IpcConnectionCreateEvent.class, this);
        registry.register(IpcConnectionDestroyEvent.class, this);
        registry.register(IpcCallCreateEvent.class, this);
        registry.register(IpcCallDestroyEvent.class, this);
    }

    @Override
    public void eventIpcConnectionCreate(IpcConnection ipcConnection) {
        LOG.trace("Handling IpcConnectionCreateEvent: {}", ipcConnection);
    }

    @Override
    public void eventIpcConnectionDestroy(IpcConnection ipcConnection) {
        LOG.trace("Handling IpcConnectionDestroyEvent: {}", ipcConnection);
    }

    @Override
    public void eventIpcSessionCreate(IpcSession ipcSession) {
        LOG.trace("Handling IpcSessionCreateEvent: {}", ipcSession);
    }

    @Override
    public void eventIpcSessionDestroy(IpcSession ipcSession) {
        LOG.trace("Handling IpcSessionDestroyEvent: {}", ipcSession);
    }

    @Override
    public void eventIpcCallCreate(IpcCall ipcCall) {
        LOG.trace("Handling IpcCallCreateEvent: {}", ipcCall);
    }

    @Override
    public void eventIpcCallDestroy(IpcCall ipcCall) {
        LOG.trace("Handling IpcCallDestroyEvent: {}", ipcCall);
    }
    
}

/**
 * palava - a java-php-bridge
 * Copyright (C) 2007-2010  CosmoCode GmbH
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
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

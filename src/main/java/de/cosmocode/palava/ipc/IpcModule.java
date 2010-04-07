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

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;

/**
 * Binds the four custom scopes.
 * <ol>
 *   <li>{@link IpcCallScoped}</li>
 *   <li>{@link IpcConnectionScoped}</li>
 *   <li>{@link IpcSessionScoped}</li>
 *   <li>{@link IpcUnitOfWorkScope}}</li>
 * </ol>
 *
 * @author Willi Schoenborn
 * @author Tobias Sarnowski
 */
public final class IpcModule implements Module {

    @Override
    public void configure(Binder binder) {
        final IpcCallScope callScope = new ThreadLocalIpcCallScope();
        binder.bindScope(IpcCallScoped.class, callScope);
        binder.bind(IpcCallScope.class).toInstance(callScope);

        final IpcConnectionScope connectionScope = new IpcConnectionScope(callScope);
        binder.bindScope(IpcConnectionScoped.class, connectionScope);
        binder.bind(IpcConnectionScope.class).toInstance(connectionScope);

        final IpcSessionScope sessionScope = new IpcSessionScope(connectionScope);
        binder.bindScope(IpcSessionScoped.class, sessionScope);
        binder.bind(IpcSessionScope.class).toInstance(sessionScope);
    }

    /**
     * Provides the current call.
     * 
     * @param scope the call scope
     * @return the current call or null, if there is no call scope in progress
     */
    @Provides
    @IpcCallScoped
    IpcCall provideCall(IpcCallScope scope) {
        return scope.get();
    }
    
    /**
     * Provides the current connection.
     * 
     * @param scope the connection scope
     * @return the current connection or null, if there is no connection scope in progress
     */
    @Provides
    @IpcConnectionScoped
    IpcConnection provideConnection(IpcConnectionScope scope) {
        return scope.get();
    }
    
    /**
     * Provides the current session.
     * 
     * @param scope the session scope
     * @return the current session or null, if there is no session scope in progress 
     */
    @Provides
    @IpcSessionScoped
    IpcSession provideSession(IpcSessionScope scope) {
        return scope.get();
    }
    
}

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

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Provides;

/**
 * Binds the four custom scopes.
 * <ol>
 *   <li>{@link IpcCallScoped}</li>
 *   <li>{@link IpcConnectionScoped}</li>
 *   <li>{@link IpcSessionScoped}</li>
 *   <li>{@link ConnectionAwareUnitOfWorkScope}</li>
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
    IpcCall provideCall(IpcCallScope scope) {
        return scope.get();
    }

    /**
     * Provides the current call annotated with {@link Current}.
     * 
     * @param scope the call scope
     * @return the current call or null, if there is no call scope in progress
     */
    @Provides
    @Current
    IpcCall provideCurrentCall(IpcCallScope scope) {
        return scope.get();
    }
    
    /**
     * Provides the current connection.
     * 
     * @param scope the connection scope
     * @return the current connection or null, if there is no connection scope in progress
     */
    @Provides
    IpcConnection provideConnection(IpcConnectionScope scope) {
        return scope.get();
    }
    
    /**
     * Provides the current connection annotated with {@link Current}.
     * 
     * @param scope the connection scope
     * @return the current connection or null, if there is no connection scope in progress
     */
    @Provides
    @Current
    IpcConnection provideCurrentConnection(IpcConnectionScope scope) {
        return scope.get();
    }
    
    /**
     * Provides the current session.
     * 
     * @param scope the session scope
     * @return the current session or null, if there is no session scope in progress 
     */
    @Provides
    IpcSession provideSession(IpcSessionScope scope) {
        return scope.get();
    }
    
    /**
     * Provides the current session annotated with {@link Current}.
     * 
     * @param scope the session scope
     * @return the current session or null, if there is no session scope in progress 
     */
    @Provides
    @Current
    IpcSession provideCurrentSession(IpcSessionScope scope) {
        return scope.get();
    }
    
}

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

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Provider;

import de.cosmocode.palava.core.inject.Providers;
import de.cosmocode.palava.scope.AbstractScope;
import de.cosmocode.palava.scope.ScopeContext;

/**
 * Default {@link IpcConnectionScope} implementation. 
 * 
 * @author Willi Schoenborn
 * @author Tobias Sarnowski
 */
final class DefaultIpcConnectionScope extends AbstractScope implements IpcConnectionScope {

    private Provider<IpcCall> currentCall = Providers.nullProvider();

    @Inject
    void setCurrentCall(@Current Provider<IpcCall> currentCall) {
        this.currentCall = Preconditions.checkNotNull(currentCall, "CurrentCall");
    }
   
    @Override
    public boolean isActive() {
        return currentCall.get() != null;
    }
    
    private IpcConnection getConnection(IpcCall call) {
        return call == null ? null : call.getConnection();
    }
    
    @Override
    public ScopeContext get() {
        return getConnection(currentCall.get());
    }
    
}

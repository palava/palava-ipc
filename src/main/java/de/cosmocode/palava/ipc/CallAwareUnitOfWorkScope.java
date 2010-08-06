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
import com.google.inject.Provider;

import de.cosmocode.palava.scope.AbstractScope;
import de.cosmocode.palava.scope.ScopeContext;
import de.cosmocode.palava.scope.UnitOfWorkScope;

/**
 * An implementation of the {@link UnitOfWorkScope} which delegates to {@link IpcConnectionScope}
 * or another implementation of the {@link UnitOfWorkScope} respectively.
 *
 * @author Willi Schoenborn
 */
public final class CallAwareUnitOfWorkScope extends AbstractScope<ScopeContext> implements UnitOfWorkScope {

    private static final Logger LOG = LoggerFactory.getLogger(CallAwareUnitOfWorkScope.class);

    private final Provider<IpcCall> provider;
    
    private final UnitOfWorkScope workScope;

    public CallAwareUnitOfWorkScope(UnitOfWorkScope workScope, Provider<IpcCall> provider) {
        this.workScope = Preconditions.checkNotNull(workScope, "WorkScope");
        this.provider = Preconditions.checkNotNull(provider, "Provider");
    }

    @Override
    public void begin() {
        if (provider.get() == null) {
            LOG.trace("Outside of call scope, entering unit of work");
            workScope.begin();
        }
    }
    
    @Override
    public boolean inProgress() {
        return provider.get() == null ? workScope.inProgress() : true;
    }
    
    @Override
    public void end() {
        if (provider.get() == null) {
            LOG.trace("Outside of call scope, exiting unit of work");
            workScope.end();
        }
    }
    
    @Override
    public ScopeContext get() {
        final IpcCall call = provider.get();
        if (call == null) {
            LOG.trace("Outside of call scope, using unit work");
            return workScope.get();
        } else {
            LOG.trace("Inside of call scope");
            return call;
        }
    }
    
}

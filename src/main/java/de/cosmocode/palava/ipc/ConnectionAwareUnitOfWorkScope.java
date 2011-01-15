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
import com.google.inject.Key;
import com.google.inject.Provider;

import de.cosmocode.palava.scope.Delegate;
import de.cosmocode.palava.scope.UnitOfWorkScope;

/**
 * An implementation of the {@link UnitOfWorkScope} which delegates to {@link IpcConnectionScope}
 * or another implementation of the {@link UnitOfWorkScope} respectively.
 *
 * @author Willi Schoenborn
 */
final class ConnectionAwareUnitOfWorkScope implements UnitOfWorkScope {

    private static final Logger LOG = LoggerFactory.getLogger(ConnectionAwareUnitOfWorkScope.class);

    private IpcConnectionScope connectionScope;
    private UnitOfWorkScope workScope;
    
    @Inject
    void setConnectionScope(IpcConnectionScope connectionScope) {
        this.connectionScope = Preconditions.checkNotNull(connectionScope, "ConnectionScope");
    }
    
    @Inject
    void setWorkScope(@Delegate UnitOfWorkScope workScope) {
        this.workScope = Preconditions.checkNotNull(workScope, "WorkScope");
    }

    @Override
    public void begin() {
        if (connectionScope.isActive()) {
            LOG.trace("{} already in progress", connectionScope);
        } else {
            LOG.trace("Outside of {}, entering {}", connectionScope, workScope);
            workScope.begin();
        }
    }
    
    @Override
    public boolean isActive() {
        return connectionScope.isActive() || workScope.isActive();
    }
    
    @Override
    public <T> Provider<T> scope(Key<T> key, Provider<T> unscoped) {
        if (connectionScope.isActive()) {
            LOG.trace("{} currently in progress, delegating...", connectionScope);
            return connectionScope.scope(key, unscoped);
        } else {
            LOG.trace("Outside of {}, falling back to {}", connectionScope, workScope);
            return workScope.scope(key, unscoped);
        }
    }
    
    @Override
    public void end() {
        if (connectionScope.isActive()) {
            LOG.trace("{} in progress, no need to end", connectionScope);
        } else {
            LOG.trace("Outside of {}, exiting {}", connectionScope, workScope);
            workScope.end();
        }
    }
    
}

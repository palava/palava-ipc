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
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Module;

import de.cosmocode.palava.core.DefaultRegistryModule;
import de.cosmocode.palava.core.lifecycle.Initializable;
import de.cosmocode.palava.core.lifecycle.LifecycleException;
import de.cosmocode.palava.core.lifecycle.LifecycleModule;
import de.cosmocode.palava.scope.AbstractUnitOfWorkScopeTest;
import de.cosmocode.palava.scope.UnitOfWorkScope;

/**
 * An integration test for {@link ConnectionAwareUnitOfWorkScope}.
 *
 * @author Willi Schoenborn
 */
public final class ConnectionAwareUnitOfWorkScopeIntegrationTest extends AbstractUnitOfWorkScopeTest {

    /**
     * Simple initializable service.
     *
     * @author Willi Schoenborn
     */
    private static final class InitializableService implements Initializable {

        @Inject
        private UnitOfWorkScope scope;
        
        @Override
        public void initialize() throws LifecycleException {
            scope.begin();
            scope.end();
        }
        
    }
    
    @Override
    public UnitOfWorkScope unit() {
        return Guice.createInjector(
            new LifecycleModule(),
            new DefaultRegistryModule(),
            new IpcScopeModule(),
            new ConnectionAwareUnitOfWorkScopeModule(),
            new Module() {
                
                @Override
                public void configure(Binder binder) {
                    binder.bind(InitializableService.class).asEagerSingleton();
                }
                
            }
        ).getInstance(UnitOfWorkScope.class);
    }

}

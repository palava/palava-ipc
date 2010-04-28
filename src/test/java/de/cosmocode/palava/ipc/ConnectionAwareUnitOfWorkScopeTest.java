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

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import com.google.inject.Key;
import com.google.inject.Provider;

import de.cosmocode.palava.scope.AbstractUnitOfWorkScopeTest;
import de.cosmocode.palava.scope.ThreadLocalUnitOfWorkScope;
import de.cosmocode.palava.scope.UnitOfWorkScope;

/**
 * Tests {@link ConnectionAwareUnitOfWorkScope}.
 *
 * @author Willi Schoenborn
 */
public final class ConnectionAwareUnitOfWorkScopeTest extends AbstractUnitOfWorkScopeTest {

    private static final Provider<IpcConnection> NULL_PROVIDER = new Provider<IpcConnection>() {
        
        @Override
        public IpcConnection get() {
            return null;
        }
        
    };
    
    @Override
    public UnitOfWorkScope unit() {
        return unit(NULL_PROVIDER);
    }
    
    private ConnectionAwareUnitOfWorkScope unit(Provider<IpcConnection> provider) {
        final ConnectionAwareUnitOfWorkScope unit = new ConnectionAwareUnitOfWorkScope(
            new ThreadLocalUnitOfWorkScope(), provider);
        return unit;
    }
    
    /**
     * Tests {@link ConnectionAwareUnitOfWorkScope#inProgress()} while faking an {@link IpcConnection}.
     */
    @Test
    public void connectionInProgress()  {
        final Key<Object> scopeKey = Key.get(Object.class);
        final Object expected = new Object();
        final Provider<Object> scopeProvider = new Provider<Object>() {
            
            @Override
            public Object get() {
                return expected;
            }
            
        };
        
        final IpcConnection connection = EasyMock.createMock("connection", IpcConnection.class);
        connection.set(scopeKey, scopeProvider);
        EasyMock.expectLastCall();
        EasyMock.expect(connection.get(scopeKey)).andReturn(expected);
        EasyMock.replay(connection);
        
        final Provider<IpcConnection> provider = new Provider<IpcConnection>() {
            
            @Override
            public IpcConnection get() {
                return connection;
            }
        };
        
        final UnitOfWorkScope mockScope = EasyMock.createMock("mockScope", UnitOfWorkScope.class);
        EasyMock.replay(mockScope);
        
        final ConnectionAwareUnitOfWorkScope unit = new ConnectionAwareUnitOfWorkScope(
            mockScope, provider);

        Assert.assertTrue(unit.inProgress());
        unit.begin();
        Assert.assertTrue(unit.inProgress());
        final Object actual = unit.scope(scopeKey, scopeProvider).get();
        Assert.assertSame(expected, actual);
        unit.end();
        Assert.assertTrue(unit.inProgress());
    }

}

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
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */

package de.cosmocode.palava.ipc;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

import com.google.inject.Key;
import com.google.inject.Provider;

import de.cosmocode.palava.scope.AbstractUnitOfWorkScopeTest;
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
        final ConnectionAwareUnitOfWorkScope unit = new ConnectionAwareUnitOfWorkScope();
        unit.setProvider(provider);
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
        
        final ConnectionAwareUnitOfWorkScope unit = new ConnectionAwareUnitOfWorkScope(mockScope);
        unit.setProvider(provider);

        Assert.assertTrue(unit.inProgress());
        unit.begin();
        Assert.assertTrue(unit.inProgress());
        final Object actual = unit.scope(scopeKey, scopeProvider).get();
        Assert.assertSame(expected, actual);
        unit.end();
        Assert.assertTrue(unit.inProgress());
    }

}

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

import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Test;

/**
 * Tests {@link IpcCallFilterChainFactory} implementations.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractIpcCallFilterChainFactoryTest {

    /**
     * Provides the unit under testing.
     * 
     * @return a new {@link IpcCallFilterChainFactory}
     */
    protected abstract IpcCallFilterChainFactory unit(); 
    
    /**
     * Tests {@link IpcCallFilterChainFactory#create(List, IpcCallFilterChain)} using
     * a null list.
     */
    @Test(expected = NullPointerException.class)
    public void nullFilters() {
        final IpcCallFilterChain chain = EasyMock.createMock("chain", IpcCallFilterChain.class);
        EasyMock.replay(chain);
        unit().create(null, chain);
        EasyMock.verify(chain);
    }

    /**
     * Tests {@link IpcCallFilterChainFactory#create(List, IpcCallFilterChain)} using
     * a null chain.
     */
    @Test(expected = NullPointerException.class)
    public void nullChain() {
        final List<IpcCallFilter> filters = Collections.emptyList();
        unit().create(filters, null);
    }
    
}

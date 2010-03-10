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

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.cosmocode.palava.core.scope.ScopeContext;

/**
 * Tests {@link ScopeContext} implementations.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractIpcScopeContextTest {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractIpcScopeContextTest.class);

    /**
     * Provide a new unit under testing.
     * 
     * @return the current unit
     */
    protected abstract ScopeContext unit();

    /**
     * Tests {@link ScopeContext#set(Object, Object)}.
     */
    @Test
    public void set() {
        Assert.fail("Not yet implemented");
    }
    
    /**
     * Tests {@link ScopeContext#get(Object)}.
     */
    @Test
    public void get() {
        Assert.fail("Not yet implemented"); 
    }
    
    /**
     * Tests {@link ScopeContext#contains(Object)}.
     */
    @Test
    public void contains() {
        Assert.fail("Not yet implemented");
    }
    
    /**
     * Tests {@link ScopeContext#remove(Object)}.
     */
    @Test
    public void remove() {
        Assert.fail("Not yet implemented");
    }
    
    /**
     * Tests {@link ScopeContext#putAll(Map)}.
     */
    @Test
    public void putAll() {
        Assert.fail("Not yet implemented");
    }
    
    /**
     * Tests {@link ScopeContext#iterator()}.
     */
    @Test
    public void iterator() {
        Assert.fail("Not yet implemented");
    }
    
}

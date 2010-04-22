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

import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import de.cosmocode.palava.scope.ScopeContext;

/**
 * Tests {@link ScopeContext} implementations.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractIpcScopeContextTest {

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

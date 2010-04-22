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

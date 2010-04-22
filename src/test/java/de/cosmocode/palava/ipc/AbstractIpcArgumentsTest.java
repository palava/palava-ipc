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

import junit.framework.Assert;

import org.junit.Test;

import de.cosmocode.collections.utility.map.MapTest;

/**
 * Tests {@link IpcArguments} implementations.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractIpcArgumentsTest extends MapTest {

    @Override
    protected abstract IpcArguments unit();
 
    /**
     * Tests {@link IpcArguments#require(String...)} with a present key.
     */
    @Test
    public void require() {
        final IpcArguments unit = unit();
        unit.put("key", "value");
        Assert.assertTrue("'key' should be present", unit.containsKey("key"));
        unit.require("key");
    }
    
    /**
     * Tests {@link IpcArguments#require(String...)} with a missing key.
     */
    @Test(expected = IpcArgumentsMissingException.class)
    public void requireFail() {
        unit().require("no-such-key");
    }
    
}

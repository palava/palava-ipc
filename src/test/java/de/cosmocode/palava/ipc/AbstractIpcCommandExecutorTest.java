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

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests {@link IpcCommandExecutor} implementations.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractIpcCommandExecutorTest {

    /**
     * Provides the unit under testing.
     * 
     * @return a new {@link IpcCommandExecutor}
     */
    protected abstract IpcCommandExecutor unit();

    /**
     * An {@link IpcCommand} which has a class name
     * and can therefore be found using {@link Class#forName(String)} and created by guice.
     * The command puts it's name in the result map.
     *
     * @author Willi Schoenborn
     */
    public static class VoidCommand implements IpcCommand {
        
        @Override
        public void execute(IpcCall call, Map<String, Object> result) throws IpcCommandExecutionException {
            result.put("name", getClass().getName());
        }
        
    }
    
    /**
     * Tests {@link IpcCommandExecutor#execute(String, IpcCall)} with a valid name.
     * 
     * @throws IpcCommandExecutionException if execution failed, should not happen
     */
    @Test
    public void execute() throws IpcCommandExecutionException {
        final IpcCall call = EasyMock.createMock("call", IpcCall.class);
        EasyMock.replay(call);
        final String name = VoidCommand.class.getName();
        final Map<String, Object> result = unit().execute(name, call);
        final Object returnedName = result.get("name");
        Assert.assertNotNull(returnedName);
        Assert.assertEquals(name, returnedName);
        EasyMock.verify(call);
    }
    
    /**
     * Tests {@link IpcCommandExecutor#execute(String, IpcCall)} with a null name.
     * 
     * @throws IpcCommandExecutionException if execution failed, should not happen
     */
    @Test(expected = NullPointerException.class)
    public void nullName() throws IpcCommandExecutionException {
        final IpcCall call = EasyMock.createMock("call", IpcCall.class);
        EasyMock.replay(call);
        unit().execute(null, call);
    }
    
    /**
     * Tests {@link IpcCommandExecutor#execute(String, IpcCall)} with a null call.
     * 
     * @throws IpcCommandExecutionException if execution failed, should not happen
     */
    @Test(expected = NullPointerException.class)
    public void nullCall() throws IpcCommandExecutionException {
        unit().execute(VoidCommand.class.getName(), null);
    }
    
    /**
     * Tests {@link IpcCommandExecutor#execute(String, IpcCall)} with an invalid name.
     * 
     * @throws IpcCommandExecutionException if execution failed, expected
     */
    @Test(expected = IpcCommandNotAvailableException.class)
    public void illegalName() throws IpcCommandExecutionException {
        final IpcCall call = EasyMock.createMock("call", IpcCall.class);
        EasyMock.replay(call);
        unit().execute("no.such.Command", call);
    }
    
}

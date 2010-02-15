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

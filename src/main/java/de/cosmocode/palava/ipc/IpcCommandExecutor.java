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

/**
 * Provides the interface for a Command Executor
 * implementation.
 *
 * @author Tobias Sarnowski
 */
public interface IpcCommandExecutor {

    /**
     * Execute the given command and the given call, respecting the filter chain.
     *
     * @param name the name of the command to execute
     * @param call the call to execute the command with
     * @return the result of the command execution
     * @throws NullPointerException if name or call is null
     * @throws IpcCommandExecutionException if something goes wrong, return this exception to the caller
     */
    Map<String, Object> execute(String name, IpcCall call) throws IpcCommandExecutionException;

}

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

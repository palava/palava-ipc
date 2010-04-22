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

import de.cosmocode.palava.scope.ScopeContext;

/**
 * A call symbolizes the request to execute a {@link IpcCommand} with
 * all given informations.
 *
 * @author Tobias Sarnowski
 */
public interface IpcCall extends ScopeContext {

    /**
     * The call related connection.
     *
     * @return must not be null
     */
    IpcConnection getConnection();

    /**
     * All given arguments for the request.
     * 
     * @return must not be null
     */
    IpcArguments getArguments();

}

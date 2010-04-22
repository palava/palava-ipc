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

/**
 * The implementing object provides the session management
 * for {@link IpcCommand} calls.
 *
 * @author Tobias Sarnowski
 */
public interface IpcSessionProvider {

    /**
     * The only way to access a session.
     *
     * @param sessionId the unique session identifier used by the caller, may be null
     * @param identifier an as unique as possible identifier for the caller
     *        determined by someone else than the caller, may be null
     * @return must not be null, provides the requested session or a new one
     */
    IpcSession getSession(String sessionId, String identifier);

}

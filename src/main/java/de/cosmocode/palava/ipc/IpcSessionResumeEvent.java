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

import java.io.Serializable;
import java.util.Map;

/**
 * Will be triggered as soon as a session will be resumed.
 *
 * @author Tobias Sarnowski
 */
public interface IpcSessionResumeEvent {

    /**
     * Allows to hook into session resuming process to
     * restore specific elements.
     * 
     * @param session the session which will be resumed
     * @param data the deserialized context information
     */
    void eventIpcSessionResume(IpcSession session, Map<String, ? extends Serializable> data);

}

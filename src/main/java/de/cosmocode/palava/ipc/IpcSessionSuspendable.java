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
 * Lifecycle class for scoped objects.
 *
 * @author Tobias Sarnowski
 */
public interface IpcSessionSuspendable {

    /**
     * Will be triggered when the corresponding context gets suspended.
     *
     * @param data a map to store meta informations for the later resume
     */
    void suspend(Map<String, ? super Serializable> data);

    /**
     * Will be triggered when the corresponding context gets resumed.
     *
     * @param data the map, created on suspend
     */
    void resume(Map<String, ? extends Serializable> data);

}

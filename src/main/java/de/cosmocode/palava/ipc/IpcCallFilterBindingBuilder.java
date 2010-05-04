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

import com.google.inject.Key;

/**
 * A filter binder is used by the {@link FilterModule} to allow
 * binding filters to specific commands.
 *
 * @author Willi Schoenborn
 */
public interface IpcCallFilterBindingBuilder {

    /**
     * Configures the matching to run with the filter specified 
     * by the given class.
     * 
     * @param type the filter type
     * @throws NullPointerException if type is null
     */
    void through(Class<? extends IpcCallFilter> type);
    
    /**
     * Configures the matching to run with the filter specified
     * by the given key.
     * 
     * @param key the filter key
     * @throws NullPointerException if key is null
     */
    void through(Key<? extends IpcCallFilter> key);
    
    /**
     * Configures the matching to run with the specified filter.
     * 
     * @since 1.3
     * @param filter the filter being used
     * @throws NullPointerException if filter is null
     */
    void through(IpcCallFilter filter);
    
}

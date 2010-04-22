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
 * A chain containing none or many filters which will
 * be called before, after or instead of the requested command.
 *
 * @author Willi Schoenborn
 */
public interface IpcCallFilterChain {

    /**
     * Proceed execution of this filter chain. The chain may not complete.
     * It's up to the included filters to totally intercept an incoming call.
     * 
     * @param call the incoming call
     * @param command the command scheduled to process the call
     * @return the generated content
     * @throws IpcCommandExecutionException if filtering failed
     */
    Map<String, Object> filter(IpcCall call, IpcCommand command) throws IpcCommandExecutionException;
    
}

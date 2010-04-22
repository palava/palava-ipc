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

import java.util.List;

/**
 * A factory for {@link IpcCallFilterChain}s.
 *
 * @author Willi Schoenborn
 */
public interface IpcCallFilterChainFactory {

    /**
     * Creates an {@link IpcCallFilterChain} using all bound {@link IpcCallFilter}s
     * and the specified proceeding chain which itself will be called if no filter intercepted
     * the call.
     * 
     * @param proceedingChain the proceeding filter chain
     * @return a new {@link IpcCallFilterChain}
     * @throws NullPointerException if proceedingChain is null
     */
    IpcCallFilterChain create(IpcCallFilterChain proceedingChain);
    
    /**
     * Creates an {@link IpcCallFilterChain} using the specified filter and the proceeding chain
     * which itself will be called if no filter intercepted the call.
     * 
     * @param filters the list of filters
     * @param proceedingChain the proceeding filter chain
     * @return a new {@link IpcCallFilterChain}
     * @throws NullPointerException if filters or proceedingChain is null
     */
    IpcCallFilterChain create(List<IpcCallFilter> filters, IpcCallFilterChain proceedingChain);
    
}

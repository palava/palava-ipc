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

import com.google.common.base.Preconditions;

/**
 * Default implementation of the {@link IpcCallFilterChain} interface.
 *
 * @author Willi Schoenborn
 */
final class DefaultIpcCallFilterChain extends AbstractIpcCallFilterChain {

    private final List<IpcCallFilter> filters;
    
    private final IpcCallFilterChain proceedingChain;

    public DefaultIpcCallFilterChain(List<IpcCallFilter> filters, IpcCallFilterChain proceedingChain) {
        this.filters = Preconditions.checkNotNull(filters, "Filter");
        this.proceedingChain = Preconditions.checkNotNull(proceedingChain, "ProceedingChain");
    }
    
    @Override
    protected List<IpcCallFilter> getFilters() {
        return filters;
    }
    
    @Override
    protected IpcCallFilterChain proceedingChain() {
        return proceedingChain;
    }
    
}

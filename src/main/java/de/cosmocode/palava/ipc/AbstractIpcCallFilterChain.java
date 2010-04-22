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
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract base implementation of the {@link IpcCallFilterChain} interface.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractIpcCallFilterChain implements IpcCallFilterChain {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractIpcCallFilterChain.class);

    private int index = -1;

    /**
     * Provides the filters which are part of this chain.
     * 
     * @return a list of filters
     */
    protected abstract List<IpcCallFilter> getFilters();
    
    /**
     * Provides the proceeding filter chain which will be called when
     * all filters have been executed and no filter intercepted the call.
     * 
     * @return the proceeding filterchain
     */
    protected abstract IpcCallFilterChain proceedingChain();
    
    @Override
    public Map<String, Object> filter(IpcCall call, IpcCommand command) throws IpcCommandExecutionException {
        index++;
        if (index < getFilters().size()) {
            final IpcCallFilter filter = getFilters().get(index);
            LOG.trace("Filtering {} with {}", call, filter);
            return filter.filter(call, command, this);
        } else {
            return proceedingChain().filter(call, command);
        }
    }

}

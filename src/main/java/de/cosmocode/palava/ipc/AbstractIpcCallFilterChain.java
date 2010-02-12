/**
 * palava - a java-php-bridge
 * Copyright (C) 2007-2010  CosmoCode GmbH
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
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

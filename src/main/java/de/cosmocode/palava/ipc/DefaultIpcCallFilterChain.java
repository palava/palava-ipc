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

import com.google.common.base.Preconditions;

/**
 * Default implementation of the {@link IpcCallFilterChain} interface which iterates
 * a given list of {@link IpcCallFilter}s and delegates to a proceeding chain
 * if no filter intercepted the call.
 *
 * @author Willi Schoenborn
 */
final class DefaultIpcCallFilterChain implements IpcCallFilterChain {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultIpcCallFilterChain.class);

    private final List<IpcCallFilter> filters;
    
    private final IpcCallFilterChain proceedingChain;
    
    private int index = -1;
    
    public DefaultIpcCallFilterChain(List<IpcCallFilter> filters, IpcCallFilterChain proceedingChain) {
        this.filters = Preconditions.checkNotNull(filters, "Filters");
        this.proceedingChain = Preconditions.checkNotNull(proceedingChain, "ProceedingChain");
    }

    @Override
    public Map<String, Object> filter(IpcCall call) throws IpcCallFilterException {
        index++;
        if (index < filters.size()) {
            final IpcCallFilter filter = filters.get(index);
            LOG.debug("Filtering {} with {}", call, filter);
            return filter.filter(call, this);
        } else {
            return proceedingChain.filter(call);
        }
    }

}

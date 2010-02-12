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

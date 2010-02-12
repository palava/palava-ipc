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

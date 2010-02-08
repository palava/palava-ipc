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

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * Static utility class for {@link IpcCallFilter}s and {@link IpcCallFilterChain}.
 *
 * @author Willi Schoenborn
 */
public final class IpcFiltering {

    private static final Logger LOG = LoggerFactory.getLogger(IpcFiltering.class);

    private IpcFiltering() {
        
    }

	/**
     * Composes a {@link Predicate} and an {@link IpcCallFilter} which executes the specified
     * filter if and only if the specified predicate returns true for a given command.
     * 
     * @param predicate the predicate which decides whether the specified filter should be applied
     *        to an incoming call.
     * @param filter the backed filter
     * @return a composed filter which skips filter execution if the specified predicate does not apply
     */
    public static IpcCallFilter compose(Predicate<? super IpcCommand> predicate, IpcCallFilter filter) {
        return new ComposedIpcCallFilterDefinition(predicate, filter);
    }
    
    /**
     * Composes a {@link Predicate} and an {@link IpcCallFilter}.
     *
     * @author Willi Schoenborn
     */
    private static final class ComposedIpcCallFilterDefinition implements IpcCallFilter {

        private final Predicate<? super IpcCommand> predicate;
        
        private final IpcCallFilter filter;
        
        public ComposedIpcCallFilterDefinition(Predicate<? super IpcCommand> predicate, IpcCallFilter filter) {
            this.predicate = Preconditions.checkNotNull(predicate, "Predicate");
            this.filter = Preconditions.checkNotNull(filter, "Filter");
        }
        
        @Override
        public Map<String, Object> filter(IpcCall call, IpcCallFilterChain chain) throws IpcCallFilterException {
            if (predicate.apply(call.command())) {
                LOG.debug("IpcFiltering {} using {}", call, filter);
                return filter.filter(call, chain);
            } else {
                LOG.debug("Skipping filter executing of {} for {}", filter, call);
                return chain.filter(call);
            }
        }
        
        @Override
        public String toString() {
            return String.format("IpcFiltering.compose(%s, %s)", filter, predicate);
        }
        
    }
    
}

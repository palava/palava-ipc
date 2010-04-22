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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * Static utility class for {@link IpcCallFilter}s and {@link IpcCallFilterChain}.
 *
 * @author Willi Schoenborn
 */
final class IpcCallFiltering {

    private static final Logger LOG = LoggerFactory.getLogger(IpcCallFiltering.class);

    private IpcCallFiltering() {
        
    }

    /**
     * Composes a {@link Predicate} and an {@link IpcCallFilter} which executes the specified
     * filter if and only if the specified predicate returns true for a given command.
     * 
     * @param predicate the predicate which decides whether the specified filter should be applied
     *        to an incoming call.
     * @param filter the backed filter
     * @return a composed filter which skips filter execution if the specified predicate does not apply
     * @throws NullPointerException if predicate or filter is null
     */
    public static IpcCallFilter compose(Predicate<? super IpcCommand> predicate, IpcCallFilter filter) {
        Preconditions.checkNotNull(predicate, "Predicate");
        Preconditions.checkNotNull(filter, "Filter");
        return predicate == Commands.any() ? filter : new ComposedIpcCallFilter(predicate, filter);
    }
    
    /**
     * Composes a {@link Predicate} and an {@link IpcCallFilter}.
     *
     * @author Willi Schoenborn
     */
    private static final class ComposedIpcCallFilter implements IpcCallFilter {

        private final Predicate<? super IpcCommand> predicate;
        
        private final IpcCallFilter filter;
        
        public ComposedIpcCallFilter(Predicate<? super IpcCommand> predicate, IpcCallFilter filter) {
            this.predicate = Preconditions.checkNotNull(predicate, "Predicate");
            this.filter = Preconditions.checkNotNull(filter, "Filter");
        }
        
        @Override
        public Map<String, Object> filter(IpcCall call, IpcCommand command, IpcCallFilterChain chain) 
            throws IpcCommandExecutionException {
            if (predicate.apply(command)) {
                LOG.trace("Filtering {} using {}", call, filter);
                return filter.filter(call, command, chain);
            } else {
                LOG.trace("Skipping filter executing of {} for {}", filter, call);
                return chain.filter(call, command);
            }
        }
        
        @Override
        public String toString() {
            return String.format("IpcFiltering.compose(%s, %s)", filter, predicate);
        }
        
    }
    
}

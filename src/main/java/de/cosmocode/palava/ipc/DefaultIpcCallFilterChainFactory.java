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

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.inject.Binding;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;

/**
 * Default implementation of the {@link IpcCallFilterChainFactory} interface.
 *
 * @author Willi Schoenborn
 */
final class DefaultIpcCallFilterChainFactory implements IpcCallFilterChainFactory {
        
    private final ImmutableList<IpcCallFilter> defaultFilters;
    
    @Inject
    public DefaultIpcCallFilterChainFactory(final Injector injector) {
        Preconditions.checkNotNull(injector, "Injector");
        
        final ImmutableList.Builder<IpcCallFilter> builder = ImmutableList.builder();

        for (Binding<?> entry : injector.findBindingsByType(IpcCallFilterDefinition.LITERAL)) {
            // guarded by findBindingsByType()
            @SuppressWarnings("unchecked")
            final Key<List<IpcCallFilterDefinition>> key = (Key<List<IpcCallFilterDefinition>>) entry.getKey();
            
            final Function<IpcCallFilterDefinition, IpcCallFilter> function;
            function = new Function<IpcCallFilterDefinition, IpcCallFilter>() {
                
                @Override
                public IpcCallFilter apply(IpcCallFilterDefinition input) {
                    return IpcCallFiltering.compose(input.getPredicate(), injector.getInstance(input.getKey()));
                }
                
            };
            
            builder.addAll(Iterables.transform(injector.getInstance(key), function));
        }
        
        this.defaultFilters = builder.build();
    }
    
    public DefaultIpcCallFilterChainFactory(ImmutableList<IpcCallFilter> defaultFilters) {
        this.defaultFilters = Preconditions.checkNotNull(defaultFilters, "DefaultFilters");
    }

    @Override
    public IpcCallFilterChain create(IpcCallFilterChain proceedingChain) {
        return create(defaultFilters, proceedingChain);
    }
    
    @Override
    public IpcCallFilterChain create(List<IpcCallFilter> filters, IpcCallFilterChain proceedingChain) {
        Preconditions.checkNotNull(filters, "Filters");
        Preconditions.checkNotNull(proceedingChain, "ProceedingChain");
        if (filters.isEmpty()) {
            return proceedingChain;
        } else {
            return new DefaultIpcCallFilterChain(filters, proceedingChain);
        }
    }
    
}

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
import com.google.common.collect.ImmutableList;
import com.google.inject.Binding;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;

/**
 * Default implementation of the {@link IpcCallFilterChainFactory} interface.
 *
 * @author Willi Schoenborn
 */
@Singleton
final class DefaultIpcCallFilterChainFactory implements IpcCallFilterChainFactory {

    static final TypeLiteral<List<IpcCallFilter>> LITERAL = new TypeLiteral<List<IpcCallFilter>>() { };
    
    private final ImmutableList<IpcCallFilter> defaultFilters;
    
    @Inject
    public DefaultIpcCallFilterChainFactory(Injector injector) {
        Preconditions.checkNotNull(injector, "Injector");
        
        final ImmutableList.Builder<IpcCallFilter> builder = ImmutableList.builder();

        for (Binding<?> entry : injector.findBindingsByType(LITERAL)) {
            // guarded by findBindingsByType()
            @SuppressWarnings("unchecked")
            final Key<List<IpcCallFilter>> key = (Key<List<IpcCallFilter>>) entry.getKey();
            builder.addAll(injector.getInstance(key));
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

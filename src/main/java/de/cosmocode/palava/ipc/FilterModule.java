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
import com.google.common.base.Predicate;
import com.google.inject.Binder;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import com.google.inject.internal.Lists;
import com.google.inject.internal.UniqueAnnotations;

/**
 * Abstract module which allows binding filters to command.
 *
 * @author Willi Schoenborn
 */
public abstract class FilterModule implements Module {

    private final List<IpcCallFilterDefinition> definitions = Lists.newArrayList();
    
    @Override
    public final void configure(Binder binder) {
        configure();
        
        final TypeLiteral<List<IpcCallFilterDefinition>> literal = IpcCallFilterDefinition.LITERAL;
        final Key<List<IpcCallFilterDefinition>> key = Key.get(literal, UniqueAnnotations.create());
        binder.bind(key).toInstance(definitions);
    }
    
    /**
     * Configures the filter instances.
     */
    protected abstract void configure();
    
    /**
     * Creates a filter binding builder for the given predicate.
     * 
     * @param predicate a predicate which matches all command the following filter
     *        will be bound to
     * @return a new filter binding builder
     * @throws NullPointerException if predicate is null
     */
    protected IpcCallFilterBindingBuilder filter(Predicate<? super IpcCommand> predicate) {
        return new InternalIpcCallFilterBindingBuilder(predicate);
    }
    
    /**
     * Internal implementation of the {@link IpcCallFilterBindingBuilder} interface.
     *
     * @author Willi Schoenborn
     */
    private final class InternalIpcCallFilterBindingBuilder implements IpcCallFilterBindingBuilder {
        
        private final Predicate<? super IpcCommand> predicate;
        
        public InternalIpcCallFilterBindingBuilder(Predicate<? super IpcCommand> predicate) {
            this.predicate = Preconditions.checkNotNull(predicate, "Predicate");
        }
        
        @Override
        public void through(Class<? extends IpcCallFilter> type) {
            through(Key.get(type));
        }
        
        @Override
        public void through(Key<? extends IpcCallFilter> key) {
            definitions.add(new InternalIpcCallFilterDefinition(key));
        }
        
        /**
         * Internal implementation of the {@link IpcCallFilterDefinition} interface.
         *
         * @author Willi Schoenborn
         */
        private final class InternalIpcCallFilterDefinition implements IpcCallFilterDefinition {
            
            private final Key<? extends IpcCallFilter> key;
            
            public InternalIpcCallFilterDefinition(Key<? extends IpcCallFilter> key) {
                this.key = Preconditions.checkNotNull(key, "Key");
            }
            
            @Override
            public Predicate<? super IpcCommand> getPredicate() {
                return predicate;
            }
            
            @Override
            public Key<? extends IpcCallFilter> getKey() {
                return key;
            }
            
        }
        
    }
    
}

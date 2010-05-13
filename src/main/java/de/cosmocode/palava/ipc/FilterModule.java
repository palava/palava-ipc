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

import java.util.Collections;
import java.util.List;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.inject.AbstractModule;
import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.internal.UniqueAnnotations;
import com.google.inject.util.Providers;

/**
 * Abstract module which allows binding filters to command.
 *
 * @author Willi Schoenborn
 */
public abstract class FilterModule extends AbstractModule {

    /**
     * Creates a filter binding builder for the given predicate.
     * 
     * @param predicate a predicate which matches all command the following filter
     *        will be bound to
     * @return a new filter binding builder
     * @throws NullPointerException if predicate is null
     */
    protected final IpcCallFilterBindingBuilder filter(Predicate<? super IpcCommand> predicate) {
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
            Preconditions.checkNotNull(type, "Type");
            through(Key.get(type));
        }
        
        @Override
        public void through(Key<? extends IpcCallFilter> key) {
            Preconditions.checkNotNull(key, "Key");
            bind(getProvider(key));
        }
        
        @Override
        public void through(IpcCallFilter filter) {
            Preconditions.checkNotNull(filter, "Filter");
            bind(Providers.of(filter));
        }
        
        private void bind(Provider<? extends IpcCallFilter> provider) {
            final IpcCallFilterDefinition definition = new InternalIpcCallFilterDefinition(provider);
            final List<IpcCallFilterDefinition> list = Collections.singletonList(definition);
            final Key<List<IpcCallFilterDefinition>> key = Key.get(IpcCallFilterDefinition.LITERAL, 
                UniqueAnnotations.create());
            FilterModule.this.bind(key).toInstance(list);
        }
        
        /**
         * Internal implementation of the {@link IpcCallFilterDefinition} interface.
         *
         * @author Willi Schoenborn
         */
        private final class InternalIpcCallFilterDefinition implements IpcCallFilterDefinition {
            
            private final Provider<? extends IpcCallFilter> provider;
            
            public InternalIpcCallFilterDefinition(Provider<? extends IpcCallFilter> provider) {
                this.provider = Preconditions.checkNotNull(provider, "Provider");
            }
            
            @Override
            public Predicate<? super IpcCommand> getPredicate() {
                return predicate;
            }
            
            @Override
            public IpcCallFilter getFilter() {
                return provider.get();
            }
            
        }
        
    }
    
}

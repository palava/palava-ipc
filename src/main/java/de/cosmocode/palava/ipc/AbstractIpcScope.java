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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;

/**
 * Abstract implementation of the {@link Scope} interface
 * which uses and underlying {@link IpcScopeContext} and
 * provides a meaningful implementation of the caching algorithm.
 * 
 * @param <S> generic scope type
 * @author Willi Schoenborn
 * @author Tobias Sarnowski
 */
abstract class AbstractIpcScope<S extends IpcScopeContext> implements Scope, Provider<S> {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractIpcScope.class);
    
    @Override
    public final <T> Provider<T> scope(final Key<T> key, final Provider<T> provider) {
        LOG.trace("Interception scoped request with {} to {}", key, provider);
        final IpcScopeContext context = get();
        if (context == null) {
            LOG.trace("No context present, returning {}", provider);
            return provider;
        }
        return new Provider<T>() {

            @Override
            public T get() {
                final T cached = context.<Key<T>, T>get(key);
                // is there a cached version?
                if (cached == null && !context.contains(key)) {
                    final T unscoped = provider.get();
                    context.set(key, unscoped);
                    LOG.trace("No cached version for {} found, created {}", key, unscoped);
                    return unscoped;
                } else {
                    LOG.trace("Found cached version for {}: {}", key, cached);
                    return cached;
                }
            }

        };
    }
    
}

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

    @Override
    public final <T> Provider<T> scope(final Key<T> key, final Provider<T> provider) {
        final IpcScopeContext currentContext = get();
        if (currentContext == null) return provider;
        return new Provider<T>() {

            @Override
            public T get() {
                final T cached = currentContext.<Key<T>, T>get(key);
                if (cached == null || !currentContext.contains(key)) {
                    final T unscoped = provider.get();
                    currentContext.set(key, unscoped);
                    return unscoped;
                } else {
                    return cached;
                }
            }

        };
    }
}
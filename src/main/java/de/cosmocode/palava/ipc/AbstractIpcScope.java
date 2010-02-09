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
import de.cosmocode.palava.ipc.IpcScopeContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tobias Sarnowski
 */
abstract class AbstractIpcScope implements Scope {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractIpcScope.class);

    protected abstract IpcScopeContext getScopeContext();

    @Override
    public final <T> Provider<T> scope(final Key<T> key, final Provider<T> provider) {
        return new Provider<T>() {

            @Override
            public T get() {
                final IpcScopeContext currentContext = getScopeContext();
                if (currentContext == null) return provider.get();
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

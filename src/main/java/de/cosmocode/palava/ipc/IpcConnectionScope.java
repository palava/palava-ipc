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

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Scope;

import de.cosmocode.palava.core.scope.AbstractScope;

/**
 * Custom {@link Scope} implementation for one {@linkplain IpcConnection connection}.
 * 
 * @author Willi Schoenborn
 * @author Tobias Sarnowski
 */
final class IpcConnectionScope extends AbstractScope<IpcConnection> {

    private final Provider<IpcCall> provider;

    @Inject
    public IpcConnectionScope(Provider<IpcCall> provider) {
        this.provider = Preconditions.checkNotNull(provider, "Provider");
    }

    @Override
    protected boolean inProgress() {
        final IpcCall currentCall = provider.get();
        return currentCall != null && currentCall.getConnection() != null;
    }
    
    @Override
    public IpcConnection get() {
        return provider.get().getConnection();
    }

}

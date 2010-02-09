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
import com.google.inject.*;
import de.cosmocode.palava.ipc.IpcScopeContext;
import de.cosmocode.palava.ipc.IpcSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tobias Sarnowski
 */
@Singleton
public final class IpcSessionScope extends AbstractIpcScope implements Provider<IpcSession> {

    private final Provider<IpcConnection> provider;

    private static final Logger LOG = LoggerFactory.getLogger(IpcSessionScope.class);

    @Inject
    public IpcSessionScope(Provider<IpcConnection> provider) {
        this.provider = Preconditions.checkNotNull(provider, "Provider");
    }

    @Override
    protected IpcScopeContext getScopeContext() {
        return get();
    }

    @Override
    public IpcSession get() {
        final IpcConnection currentConnection = provider.get();
        return currentConnection == null ? null : currentConnection.getSession();
    }

}

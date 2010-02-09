/**
s * palava - a java-php-bridge
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

import com.google.common.base.Preconditions;
import com.google.inject.Provider;
import com.google.inject.Scope;
import com.google.inject.Singleton;

/**
 * Custom {@link Scope} implementation for one single {@linkplain IpcCall call}.
 *
 * @author Willi Schoenborn
 * @author Tobias Sarnowski
 */
@Singleton
public final class IpcCallScope extends AbstractIpcScope implements Provider<IpcCall> {

    private static final Logger LOG = LoggerFactory.getLogger(IpcCallScope.class);

    private final ThreadLocal<IpcCall> currentCall = new ThreadLocal<IpcCall>();

    // prevent instantiation outside this package
    IpcCallScope() {

    }

    @Override
    protected IpcScopeContext getScopeContext() {
        return currentCall.get();
    }

    /**
     * Enters this scope.
     * 
     * @param call the incoming call
     * @throws NullPointerException if call is null
     * @throws IllegalStateException if there is already a call scope block in progress
     */
    public void enter(IpcCall call) {
        LOG.trace("entering call scope");
        Preconditions.checkNotNull(call, "Call");
        Preconditions.checkState(currentCall.get() == null, "There is already a call scope block in progress");
        currentCall.set(call);
    }

    /**
     * Exists this scope. This method just returns
     * if there is currently no scoping block in progress.
     */
    public void exit() {
        LOG.trace("exiting call scope");
        currentCall.remove();
    }

    @Override
    public IpcCall get() {
        return currentCall.get();
    }

}

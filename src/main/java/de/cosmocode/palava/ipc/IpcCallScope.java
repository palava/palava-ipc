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
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author Willi Schoenborn
 * @author Tobias Sarnowski
 */
@Singleton
public final class IpcCallScope extends AbstractIpcScope implements Provider<IpcCall> {

    private static final Logger LOG = LoggerFactory.getLogger(IpcCallScope.class);

    private final ThreadLocal<IpcCall> currentCall = new ThreadLocal<IpcCall>();

    /**
     * Package private!
     */
    IpcCallScope() {

    }

    @Override
    protected IpcScopeContext getScopeContext() {
        return currentCall.get();
    }

    public void enter(IpcCall call) {
        LOG.trace("entering call scope");
        Preconditions.checkNotNull(call, "Call");
        Preconditions.checkState(currentCall.get() == null, "Already in a call scope block");
        currentCall.set(call);
    }

    public void exit() {
        LOG.trace("exiting call scope");
        currentCall.remove();
    }

    @Override
    public IpcCall get() {
        return currentCall.get();
    }

}
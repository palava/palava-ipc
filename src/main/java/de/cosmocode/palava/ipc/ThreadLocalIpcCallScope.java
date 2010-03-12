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

import com.google.common.base.Preconditions;
import com.google.inject.Scope;

import de.cosmocode.palava.core.scope.AbstractScope;

/**
 * Custom {@link Scope} implementation for one single {@linkplain IpcCall call}.
 *
 * @author Willi Schoenborn
 * @author Tobias Sarnowski
 */
final class ThreadLocalIpcCallScope extends AbstractScope<IpcCall> implements IpcCallScope {

    private final ThreadLocal<IpcCall> currentCall = new ThreadLocal<IpcCall>();
    
    @Override
    public void enter(IpcCall call) {
        Preconditions.checkNotNull(call, "Call");
        currentCall.set(call);
    }

    @Override
    public void exit() {
        final IpcCall call = currentCall.get();
        Preconditions.checkState(call != null, "There is no %s block in progress", this);
        call.clear();
        currentCall.remove();
    }

    @Override
    public IpcCall get() {
        return currentCall.get();
    }

    @Override
    public String toString() {
        return IpcCallScope.class.getSimpleName();
    }
    
}

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
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */

package de.cosmocode.palava.ipc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;

import de.cosmocode.palava.core.scope.AbstractScope;
import de.cosmocode.palava.core.scope.ScopeContext;
import de.cosmocode.palava.core.scope.ThreadUnitOfWorkScope;
import de.cosmocode.palava.core.scope.UnitOfWorkScope;

/**
 * An implementation of the {@link UnitOfWorkScope} which delegates to {@link IpcConnectionScope}
 * or another implementation of the {@link UnitOfWorkScope} respectively.
 *
 * @author Willi Schoenborn
 */
public final class IpcUnitOfWorkScope extends AbstractScope<ScopeContext> implements UnitOfWorkScope {

    private static final Logger LOG = LoggerFactory.getLogger(IpcUnitOfWorkScope.class);

    private IpcConnectionScope connectionScope;
    
    private final UnitOfWorkScope workScope;

    public IpcUnitOfWorkScope(UnitOfWorkScope workScope) {
        this.workScope = Preconditions.checkNotNull(workScope, "WorkScope");
    }

    public IpcUnitOfWorkScope() {
        this(new ThreadUnitOfWorkScope());
    }
    
    @Inject
    void setConnectionScope(IpcConnectionScope connectionScope) {
        this.connectionScope = Preconditions.checkNotNull(connectionScope, "ConnectionScope");
    }

    @Override
    public void enter() {
        if (connectionScope.get() == null) {
            LOG.trace("Outside of connection scope, entering unit of work");
            workScope.enter();
        }
    }
    
    @Override
    public void exit() {
        if (connectionScope.get() == null) {
            LOG.trace("Outside of connection scope, exiting unit of work");
            workScope.exit();
        }
    }
    
    @Override
    public ScopeContext get() {
        if (connectionScope.get() == null) {
            LOG.trace("Outside of connection scope, using unit work");
            return workScope.get();
        } else {
            LOG.trace("Inside of connection scope");
            return connectionScope.get();
        }
    }
    
}

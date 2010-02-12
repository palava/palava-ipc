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

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests {@link IpcCall} implementations.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractIpcCallTest extends AbstractIpcScopeContextTest {

    @Override
    protected abstract IpcCall unit();
    
    /**
     * Tests {@link IpcCall#getConnection()}.
     */
    @Test
    public void getConnection() {
        Assert.assertNotNull(unit().getConnection());
    }
    
    /**
     * Tests {@link IpcCall#getArguments()}.
     */
    @Test
    public void getArguments() {
        Assert.assertNotNull(unit().getArguments());
    }
    
}

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

import java.util.List;

import com.google.common.base.Predicate;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;

/**
 * A filter definition contains a predicate matching commands
 * and a key addressing a filter binding in guice.
 *
 * @author Willi Schoenborn
 */
interface IpcCallFilterDefinition {

    TypeLiteral<List<IpcCallFilterDefinition>> LITERAL = new TypeLiteral<List<IpcCallFilterDefinition>>() { };
    
    /**
     * Provides the matcher predicate.
     * 
     * @return the predicate used to match commands
     */
    Predicate<? super IpcCommand> getPredicate();
    
    /**
     * Provides the guice key for the associated filter.
     * 
     * @return the filter binding key
     */
    Key<? extends IpcCallFilter> getKey();
    
}

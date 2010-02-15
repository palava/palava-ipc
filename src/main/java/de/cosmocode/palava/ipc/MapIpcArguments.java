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

import java.util.Map;

import com.google.common.base.Preconditions;

import de.cosmocode.collections.utility.Utility;
import de.cosmocode.collections.utility.UtilitySet;

/**
 * Map-based implementation of the {@link IpcArguments} interface.
 *
 * @author Willi Schoenborn
 */
public final class MapIpcArguments extends AbstractIpcArguments {

    private final Map<String, Object> arguments;
    
    public MapIpcArguments(Map<String, Object> arguments) {
        this.arguments = Preconditions.checkNotNull(arguments, "Arguments");
    }
    
    @Override
    public UtilitySet<Entry<String, Object>> entrySet() {
        return Utility.asUtilitySet(arguments.entrySet());
    }

    @Override
    public Object put(String key, Object value) {
        return arguments.put(key, value);
    }

}

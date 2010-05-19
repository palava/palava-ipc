/**
 * Copyright 2010 CosmoCode GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.cosmocode.palava.ipc;

import java.util.Collections;
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

    private static final IpcArguments EMPTY = new MapIpcArguments(Collections.<String, Object>emptyMap());
    
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

    /**
     * Returns empty immutable {@link IpcArguments}.
     * 
     * @since 1.3
     * @return empty immutable arguments
     */
    public static IpcArguments empty() {
        return EMPTY;
    }
    
}

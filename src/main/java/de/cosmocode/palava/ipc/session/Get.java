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

package de.cosmocode.palava.ipc.session;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.google.inject.Singleton;

import de.cosmocode.palava.ipc.IpcArguments;
import de.cosmocode.palava.ipc.IpcCall;
import de.cosmocode.palava.ipc.IpcCommand;
import de.cosmocode.palava.ipc.IpcCommand.Description;
import de.cosmocode.palava.ipc.IpcCommand.Param;
import de.cosmocode.palava.ipc.IpcCommand.Params;
import de.cosmocode.palava.ipc.IpcCommand.Return;
import de.cosmocode.palava.ipc.IpcCommandExecutionException;
import de.cosmocode.palava.ipc.IpcSession;

/**
 * See below.
 *
 * @since 1.3
 * @author Willi Schoenborn
 */
@Description("Retrieves the values for the specified keys.")
@Params({
    @Param(name = SessionConstants.KEYS, description = "The requested keys", type = "array"),
    @Param(name = SessionConstants.NAMESPACE, description = "The global namespace keys (null disables)", 
        type = "string", optional = true)
})
@Return(name = SessionConstants.ENTRIES, description = "The a mapping of all found entries")
@Singleton
public final class Get implements IpcCommand {

    @Override
    public void execute(IpcCall call, Map<String, Object> result) throws IpcCommandExecutionException {
        final IpcArguments arguments = call.getArguments();
        final List<Object> keys = arguments.getList(SessionConstants.KEYS);
        final String namespace = arguments.getString(SessionConstants.NAMESPACE, null);
        final IpcSession session = call.getConnection().getSession();
        
        final Map<Object, Object> entries = Maps.newHashMap();
        
        for (Object key : keys) {
            final Object value;
            
            if (namespace == null) {
                value = session.get(key);
            } else if (session.contains(namespace)) {
                final Map<Object, Object> namespaced = session.get(namespace);
                value = namespaced.get(key);
            } else {
                value = null;
            }
            
            entries.put(key, value);
        }
        
        result.put(SessionConstants.ENTRIES, entries);
    }

}

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

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
import com.google.inject.Singleton;

import de.cosmocode.palava.ipc.IpcArguments;
import de.cosmocode.palava.ipc.IpcCall;
import de.cosmocode.palava.ipc.IpcCommand;
import de.cosmocode.palava.ipc.IpcCommand.Description;
import de.cosmocode.palava.ipc.IpcCommand.Param;
import de.cosmocode.palava.ipc.IpcCommand.Params;
import de.cosmocode.palava.ipc.IpcCommandExecutionException;
import de.cosmocode.palava.ipc.IpcSession;

/**
 * See below.
 *
 * @since 1.3
 * @author Willi Schoenborn
 * @author Tobias Sarnowski
 */
@Description("Adds all specified entries to the session.")
@Params({
    @Param(name = SessionConstants.ENTRIES, description = "Map of entries"),
    @Param(name = SessionConstants.NAMESPACE, description = "The global namespace keys (null disables)", 
        type = "string", optional = true)
})
@Singleton
final class Set implements IpcCommand {

    @Override
    public void execute(IpcCall call, Map<String, Object> result) throws IpcCommandExecutionException {
        final IpcArguments arguments = call.getArguments();
        final Map<Object, Object> entries = arguments.getMap(SessionConstants.ENTRIES);
        final String namespace = arguments.getString(SessionConstants.NAMESPACE, null);
        final IpcSession session = call.getConnection().getSession();

        if (namespace == null) {
            for (Entry<Object, Object> entry : entries.entrySet()) {
                session.set(entry.getKey(), entry.getValue());
            }
        } else {
            final Map<Object, Object> namespaced;
            if (session.contains(namespace)) {
                namespaced = session.get(namespace);
            } else {
                namespaced = Maps.newHashMap();
                session.set(namespace, namespaced);
            }
            namespaced.putAll(entries);
        }
        
    }

}

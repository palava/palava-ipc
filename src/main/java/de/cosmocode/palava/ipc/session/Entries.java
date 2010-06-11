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

import com.google.common.base.Functions;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.inject.Singleton;

import de.cosmocode.palava.ipc.IpcArguments;
import de.cosmocode.palava.ipc.IpcCall;
import de.cosmocode.palava.ipc.IpcCommand;
import de.cosmocode.palava.ipc.IpcCommandExecutionException;
import de.cosmocode.palava.ipc.IpcSession;
import de.cosmocode.palava.ipc.IpcCommand.Description;
import de.cosmocode.palava.ipc.IpcCommand.Param;
import de.cosmocode.palava.ipc.IpcCommand.Params;
import de.cosmocode.palava.ipc.IpcCommand.Return;

/**
 * See below.
 *
 * @since 1.3
 * @author Willi Schoenborn
 * @author Tobias Sarnowski
 */
@Description("Retrieves all entries of the session")
@Params({
    @Param(name = SessionConstants.SORT, description = "Specifies whether entries should be sorted",
        type = "boolean", optional = true, defaultValue = "false"),
    @Param(name = SessionConstants.NAMESPACE, description = "The global namespace keys (null disables)", 
        type = "string", optional = true)
})
@Return(name = SessionConstants.ENTRIES, description = "All entries")
@Singleton
public class Entries implements IpcCommand {

    private final Ordering<Object> ordering = Ordering.natural().onResultOf(Functions.toStringFunction());
    
    @Override
    public void execute(IpcCall call, Map<String, Object> result) throws IpcCommandExecutionException {
        final IpcArguments arguments = call.getArguments();
        final boolean sort = arguments.getBoolean(SessionConstants.SORT, false);
        final String namespace = arguments.getString(SessionConstants.NAMESPACE, null);
        final IpcSession session = call.getConnection().getSession();
        
        final Map<Object, Object> entries = sort ? Maps.newTreeMap(ordering) : Maps.newHashMap();
        
        if (namespace == null) {
            for (Entry<Object, Object> entry : session) {
                entries.put(entry.getKey(), entry.getValue());
            }
        } else if (session.contains(namespace)) {
            final Map<Object, Object> namespaced = session.get(namespace);
            entries.putAll(namespaced);
        }
        
        result.put(SessionConstants.ENTRIES, entries);
    }

}

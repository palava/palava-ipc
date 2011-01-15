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

import com.google.common.base.Functions;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;
import com.google.inject.Singleton;

import de.cosmocode.palava.ipc.IpcArguments;
import de.cosmocode.palava.ipc.IpcCall;
import de.cosmocode.palava.ipc.IpcCommand;
import de.cosmocode.palava.ipc.IpcCommandExecutionException;
import de.cosmocode.palava.ipc.IpcSession;

/**
 * See below.
 *
 * @since 1.3
 * @author Willi Schoenborn
 * @author Tobias Sarnowski
 */
@IpcCommand.Description("Retrieves all entries of the session")
@IpcCommand.Params({
    @IpcCommand.Param(name = Naming.SORT, description = "Specifies whether entries should be sorted",
        type = "boolean", optional = true, defaultValue = "false"),
    @IpcCommand.Param(name = Naming.NAMESPACE, description = "The global namespace keys (null disables)", 
        type = "string", optional = true)
})
@IpcCommand.Return(name = Naming.ENTRIES, description = "All entries")
@Singleton
final class Entries implements IpcCommand {

    private final Ordering<Object> ordering = Ordering.natural().onResultOf(Functions.toStringFunction());
    
    @Override
    public void execute(IpcCall call, Map<String, Object> result) throws IpcCommandExecutionException {
        final IpcArguments arguments = call.getArguments();
        final boolean sort = arguments.getBoolean(Naming.SORT, false);
        final String namespace = arguments.getString(Naming.NAMESPACE, null);
        final IpcSession session = call.getConnection().getSession();
        
        final Map<Object, Object> entries = sort ? Maps.newTreeMap(ordering) : Maps.newHashMap();
        
        if (namespace == null) {
            entries.putAll(session);
        } else if (session.containsKey(namespace)) {
            @SuppressWarnings("unchecked")
            final Map<Object, Object> namespaced = (Map<Object, Object>) session.get(namespace);
            entries.putAll(namespaced);
        }
        
        result.put(Naming.ENTRIES, entries);
    }

}

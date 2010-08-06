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
 */
@Description("Remove entries from the session")
@Params({
    @Param(name = SessionConstants.KEYS, description = "List of keys being removed"),
    @Param(name = SessionConstants.NAMESPACE, description = "The global namespace keys (null disables)", 
        type = "string", optional = true)
})
@Singleton
public class Remove implements IpcCommand {

    @Override
    public void execute(IpcCall call, Map<String, Object> result) throws IpcCommandExecutionException {
        final IpcArguments arguments = call.getArguments();
        final List<Object> keys = arguments.getList(SessionConstants.KEYS);
        final String namespace = arguments.getString(SessionConstants.NAMESPACE, null);
        final IpcSession session = call.getConnection().getSession();

        for (Object key : keys) {
            if (namespace == null) {
                session.remove(key);
            } else if (session.contains(namespace)) {
                final Map<Object, Object> namespaced = session.get(namespace);
                namespaced.remove(key);
            }
        }
    }

}

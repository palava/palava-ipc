package de.cosmocode.palava.ipc.session;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;
import com.google.inject.Singleton;

import de.cosmocode.palava.ipc.IpcCall;
import de.cosmocode.palava.ipc.IpcCommand;
import de.cosmocode.palava.ipc.IpcCommandExecutionException;
import de.cosmocode.palava.ipc.IpcSession;
import de.cosmocode.palava.ipc.IpcCommand.Description;
import de.cosmocode.palava.ipc.IpcCommand.Return;

/**
 * See below.
 *
 * @since 1.3
 * @author Willi Schoenborn
 */
@Description("Retrieves all keys in this session")
@Return(name = SessionConstants.KEYS, description = "List of all keys")
@Singleton
public class Keys implements IpcCommand {

    @Override
    public void execute(IpcCall call, Map<String, Object> result) throws IpcCommandExecutionException {
        final IpcSession session = call.getConnection().getSession();
        
        final List<Object> keys = Lists.newArrayList();
        
        for (Entry<Object, Object> entry : session) {
            keys.add(entry.getKey());
        }
        
        result.put(SessionConstants.KEYS, keys);
    }

}

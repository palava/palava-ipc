package de.cosmocode.palava.ipc.session;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.google.inject.Singleton;

import de.cosmocode.palava.ipc.IpcArguments;
import de.cosmocode.palava.ipc.IpcCall;
import de.cosmocode.palava.ipc.IpcCommand;
import de.cosmocode.palava.ipc.IpcCommandExecutionException;
import de.cosmocode.palava.ipc.IpcSession;
import de.cosmocode.palava.ipc.IpcCommand.Description;
import de.cosmocode.palava.ipc.IpcCommand.Param;
import de.cosmocode.palava.ipc.IpcCommand.Return;

/**
 * See below.
 *
 * @since 1.3
 * @author Willi Schoenborn
 */
@Description("Retrieves the values for the specified keys.")
@Param(name = SessionConstants.KEYS, description = "The requested keys", type = "array")
@Return(name = SessionConstants.ENTRIES, description = "The a mapping of all found entries")
@Singleton
public final class Get implements IpcCommand {

    @Override
    public void execute(IpcCall call, Map<String, Object> result) throws IpcCommandExecutionException {
        final IpcArguments arguments = call.getArguments();
        final List<Object> keys = arguments.getList(SessionConstants.KEYS);
        final IpcSession session = call.getConnection().getSession();
        
        final Map<Object, Object> entries = Maps.newHashMap();
        
        for (Object key : keys) {
            final Object value = session.get(key);
            entries.put(key, value);
        }
        
        result.put(SessionConstants.ENTRIES, entries);
    }

}

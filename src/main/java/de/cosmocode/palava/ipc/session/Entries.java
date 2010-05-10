package de.cosmocode.palava.ipc.session;

import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Maps;
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
@Description("Retrieves all entries of the session")
@Return(name = SessionConstants.ENTRIES, description = "All entries")
@Singleton
public class Entries implements IpcCommand {

    @Override
    public void execute(IpcCall call, Map<String, Object> result) throws IpcCommandExecutionException {
        final IpcSession session = call.getConnection().getSession();
        
        final Map<Object, Object> entries = Maps.newHashMap();
        
        for (Entry<Object, Object> entry : session) {
            entries.put(entry.getKey(), entry.getValue());
        }
        
        result.put(SessionConstants.ENTRIES, entries);
    }

}

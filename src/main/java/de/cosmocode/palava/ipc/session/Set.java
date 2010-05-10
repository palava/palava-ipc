package de.cosmocode.palava.ipc.session;

import java.util.Map;
import java.util.Map.Entry;

import com.google.inject.Singleton;

import de.cosmocode.palava.ipc.IpcArguments;
import de.cosmocode.palava.ipc.IpcCall;
import de.cosmocode.palava.ipc.IpcCommand;
import de.cosmocode.palava.ipc.IpcCommandExecutionException;
import de.cosmocode.palava.ipc.IpcSession;
import de.cosmocode.palava.ipc.IpcCommand.Description;
import de.cosmocode.palava.ipc.IpcCommand.Param;

/**
 * See below.
 *
 * @since 1.3
 * @author Willi Schoenborn
 */
@Description("Adds all specified entries to the session.")
@Param(name = SessionConstants.ENTRIES, description = "Map of entries")
@Singleton
public final class Set implements IpcCommand {

    @Override
    public void execute(IpcCall call, Map<String, Object> result) throws IpcCommandExecutionException {
        final IpcArguments arguments = call.getArguments();
        final Map<Object, Object> entries = arguments.getMap(SessionConstants.ENTRIES);
        final IpcSession session = call.getConnection().getSession();
        
        // @Alliteration
        for (Entry<Object, Object> entry : entries.entrySet()) {
            session.set(entry.getKey(), entry.getValue());
        }
        
    }

}

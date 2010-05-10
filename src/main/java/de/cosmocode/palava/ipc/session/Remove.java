package de.cosmocode.palava.ipc.session;

import java.util.List;
import java.util.Map;

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
@Description("Remove entries from the session")
@Param(name = SessionConstants.KEYS, description = "List of keys being removed")
@Singleton
public class Remove implements IpcCommand {

    @Override
    public void execute(IpcCall call, Map<String, Object> result) throws IpcCommandExecutionException {
        final IpcArguments arguments = call.getArguments();
        final List<Object> keys = arguments.getList(SessionConstants.KEYS);
        final IpcSession session = call.getConnection().getSession();
        
        for (Object key : keys) {
            session.remove(key);
        }
    }

}

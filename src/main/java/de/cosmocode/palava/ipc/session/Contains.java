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
@Description("Checks whether the session contains all specified keys")
@Param(name = SessionConstants.KEYS, description = "The requested keys", type = "array")
@Return(name = SessionConstants.STATUS, 
    description = "A mapping of key to boolean, where keys mapped to true are contained")
@Singleton
public class Contains implements IpcCommand {

    @Override
    public void execute(IpcCall call, Map<String, Object> result) throws IpcCommandExecutionException {
        final IpcArguments arguments = call.getArguments();
        final List<Object> keys = arguments.getList(SessionConstants.KEYS);
        final IpcSession session = call.getConnection().getSession();
        
        final Map<Object, Boolean> status = Maps.newHashMap();
        
        for (Object key : keys) {
            final boolean contained = session.contains(key);
            status.put(key, Boolean.valueOf(contained));
        }
        
        result.put(SessionConstants.STATUS, status);
    }

}
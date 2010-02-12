package de.cosmocode.palava.ipc;

import java.util.Map;

import com.google.common.base.Preconditions;

import de.cosmocode.collections.utility.Utility;
import de.cosmocode.collections.utility.UtilitySet;

/**
 * Map-based implementation of the {@link IpcArguments} interface.
 *
 * @author Willi Schoenborn
 */
public final class MapIpcArguments extends AbstractIpcArguments {

    private final Map<String, Object> arguments;
    
    public MapIpcArguments(Map<String, Object> arguments) {
        this.arguments = Preconditions.checkNotNull(arguments, "Arguments");
    }
    
    @Override
    public UtilitySet<Entry<String, Object>> entrySet() {
        return Utility.asUtilitySet(arguments.entrySet());
    }

    @Override
    public Object put(String key, Object value) {
        return arguments.put(key, value);
    }

}

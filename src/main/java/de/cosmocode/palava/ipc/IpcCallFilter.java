package de.cosmocode.palava.ipc;

import java.util.Map;

/**
 * A filter can be configured to get executed on specified call, 
 * usually requesting specified {@link IpcCommand}s and runs before, after or instead
 * of the requested command.
 *
 * @author Willi Schoenborn
 */
public interface IpcCallFilter {

    /**
     * Execute this filter. This may result in proceeding the given chain or in returning 
     * a probably cached content.
     * 
     * @param call the incoming call
     * @param chain the proceeding chain
     * @return the generated content
     * @throws IpcCallFilterException if filtering failed
     */
    Map<String, Object> filter(IpcCall call, IpcCallFilterChain chain) throws IpcCallFilterException;
    
}

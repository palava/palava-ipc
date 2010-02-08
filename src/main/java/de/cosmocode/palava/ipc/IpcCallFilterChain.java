package de.cosmocode.palava.ipc;

import java.util.Map;

/**
 * A chain containing none or many filters which will
 * be called before, after or instead of the requested command.
 *
 * @author Willi Schoenborn
 */
public interface IpcCallFilterChain {

    /**
     * Proceed execution of this filter chain. The chain may not complete.
     * It's up to the included filters to totally intercept an incoming call.
     * 
     * @param call the incoming call
     * @return the generated content
     * @throws IpcCallFilterException if filtering failed
     */
    Map<String, Object> filter(IpcCall call) throws IpcCallFilterException;
    
}

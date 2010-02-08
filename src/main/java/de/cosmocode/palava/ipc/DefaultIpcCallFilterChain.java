package de.cosmocode.palava.ipc;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * Default implementation of the {@link IpcCallFilterChain} interface which iterates
 * a given list of {@link IpcCallFilter}s and delegates to a proceeding chain
 * if no filter intercepted the call.
 *
 * @author Willi Schoenborn
 */
final class DefaultIpcCallFilterChain implements IpcCallFilterChain {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultIpcCallFilterChain.class);

    private final List<IpcCallFilter> filters;
    
    private final IpcCallFilterChain proceedingChain;
    
    private int index = -1;
    
    public DefaultIpcCallFilterChain(List<IpcCallFilter> filters, IpcCallFilterChain proceedingChain) {
        this.filters = Preconditions.checkNotNull(filters, "Filters");
        this.proceedingChain = Preconditions.checkNotNull(proceedingChain, "ProceedingChain");
    }

    @Override
    public Map<String, Object> filter(IpcCall call) throws IpcCallFilterException {
        index++;
        if (index < filters.size()) {
            final IpcCallFilter filter = filters.get(index);
            LOG.debug("Filtering {} with {}", call, filter);
            return filter.filter(call, this);
        } else {
            return proceedingChain.filter(call);
        }
    }

}

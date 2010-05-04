package de.cosmocode.palava.ipc;

import java.util.Map;

import org.easymock.EasyMock;
import org.junit.Test;

import com.google.common.collect.Maps;
import com.google.inject.Guice;
import com.google.inject.Injector;

import de.cosmocode.palava.core.DefaultRegistryModule;
import de.cosmocode.palava.core.lifecycle.LifecycleModule;

/**
 * Tests {@link FilterModule}.
 *
 * @since 
 * @author Willi Schoenborn
 */
public final class FilterModuleTest {

    /**
     * Static filter class used for tests.
     *
     * @author Willi Schoenborn
     */
    private static final class ClassFilter implements IpcCallFilter {
        
        @Override
        public Map<String, Object> filter(IpcCall call, IpcCommand command, IpcCallFilterChain chain)
            throws IpcCommandExecutionException {
            return chain.filter(call, command);
        }
        
    }
    
    /**
     * Tests correct bindings of {@link FilterModule}s.
     * 
     * @since 1.3
     * @throws IpcCommandExecutionException should not happen
     */
    @Test
    public void filter() throws IpcCommandExecutionException {
        final IpcCallFilter filter = EasyMock.createMock("filter", IpcCallFilter.class);
        
        final Injector injector = Guice.createInjector(
            new LifecycleModule(),
            new DefaultRegistryModule(),
            new IpcFilterModule(),
            new FilterModule() {
                
                @Override
                protected void configure() {
                    filter(Commands.any()).through(ClassFilter.class);
                    filter(Commands.any()).through(filter);
                }
                
            }
        );
        
        final IpcCallFilterChain proceedingChain = EasyMock.createMock("proceedingChain", IpcCallFilterChain.class);
        
        final IpcCallFilterChainFactory factory = injector.getInstance(IpcCallFilterChainFactory.class);
        final IpcCallFilterChain chain = factory.create(proceedingChain);
        
        final IpcCall call = EasyMock.createMock("call", IpcCall.class);
        final IpcCommand command = EasyMock.createMock("command", IpcCommand.class);
        
        final Map<String, Object> result = Maps.newHashMap();
        EasyMock.expect(filter.filter(call, command, chain)).andReturn(result);
        
        EasyMock.replay(call, command, filter, proceedingChain);
        chain.filter(call, command);
        EasyMock.verify(call, command, filter, proceedingChain);
        
    }

}

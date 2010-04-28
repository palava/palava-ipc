package de.cosmocode.palava.ipc;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Module;

import de.cosmocode.palava.core.DefaultRegistryModule;
import de.cosmocode.palava.core.lifecycle.Initializable;
import de.cosmocode.palava.core.lifecycle.LifecycleException;
import de.cosmocode.palava.core.lifecycle.LifecycleModule;
import de.cosmocode.palava.scope.AbstractUnitOfWorkScopeTest;
import de.cosmocode.palava.scope.UnitOfWorkScope;

/**
 * An integration test for {@link ConnectionAwareUnitOfWorkScope}.
 *
 * @author Willi Schoenborn
 */
public final class ConnectionAwareUnitOfWorkScopeIntegrationTest extends AbstractUnitOfWorkScopeTest {

    /**
     * Simple initializable service.
     *
     * @author Willi Schoenborn
     */
    private static final class InitializableService implements Initializable {

        @Inject
        private UnitOfWorkScope scope;
        
        @Override
        public void initialize() throws LifecycleException {
            scope.begin();
            scope.end();
        }
        
    }
    
    @Override
    public UnitOfWorkScope unit() {
        return Guice.createInjector(
            new LifecycleModule(),
            new DefaultRegistryModule(),
            new IpcModule(),
            new ConnectionAwareUnitOfWorkScopeModule(),
            new Module() {
                
                @Override
                public void configure(Binder binder) {
                    binder.bind(InitializableService.class).asEagerSingleton();
                }
                
            }
        ).getInstance(UnitOfWorkScope.class);
    }

}

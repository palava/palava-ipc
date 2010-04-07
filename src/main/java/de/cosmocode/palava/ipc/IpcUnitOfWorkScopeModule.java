package de.cosmocode.palava.ipc;

import com.google.inject.Binder;
import com.google.inject.Module;

import de.cosmocode.palava.core.scope.UnitOfWork;
import de.cosmocode.palava.core.scope.UnitOfWorkScope;

/**
 * Binds the {@link IpcUnitOfWorkScope} to {@link UnitOfWork}.
 *
 * @author Willi Schoenborn
 */
public final class IpcUnitOfWorkScopeModule implements Module {

    @Override
    public void configure(Binder binder) {
        final IpcUnitOfWorkScope unitOfWorkScope = new IpcUnitOfWorkScope();
        binder.requestInjection(unitOfWorkScope);
        binder.bindScope(UnitOfWork.class, unitOfWorkScope);
        binder.bind(UnitOfWorkScope.class).toInstance(unitOfWorkScope);
    }

}

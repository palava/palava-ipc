package de.cosmocode.palava.ipc;

/**
 * A connection represents a set of {@linkplain IpcCall calls}.
 * 
 * @author Tobias Sarnowski
 * @author Willi Schoenborn
 */
public interface IpcConnection extends IpcScopeContext {

    /**
     * The connection related session.
     *
     * @return must not be null
     */
    IpcSession getSession();
    
}

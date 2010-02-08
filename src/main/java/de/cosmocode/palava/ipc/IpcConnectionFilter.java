package de.cosmocode.palava.ipc;

/**
 * Runs before and after every (!) {@linkplain IpcConnection connection}.
 *
 * @author Willi Schoenborn
 */
public interface IpcConnectionFilter {

    void postConnect(IpcConnection connection);
    
    void preDisconnect(IpcConnection connection);
    
}

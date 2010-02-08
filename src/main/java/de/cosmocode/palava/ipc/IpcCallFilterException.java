package de.cosmocode.palava.ipc;

/**
 * 
 *
 * @author Willi Schoenborn
 */
public final class IpcCallFilterException extends Exception {

    private static final long serialVersionUID = 5384864729505713145L;

    public IpcCallFilterException(String message) {
        super(message);
    }
    
    public IpcCallFilterException(Throwable throwable) {
        super(throwable);
    }
    
    public IpcCallFilterException(String message, Throwable throwable) {
        super(message, throwable);
    }
    
}

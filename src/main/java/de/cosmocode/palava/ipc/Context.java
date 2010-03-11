package de.cosmocode.palava.ipc;

/**
 * 
 *
 * @author Willi Schoenborn
 */
public final class Context {
    
    public static final String PREFIX = "context.";

    public static final String LOCALE = PREFIX + "locale";
    
    public static final String REQUEST_URI = PREFIX + "requestUri";
    
    public static final String REFERER = PREFIX + "referer";
    
    public static final String REMOTE_ADDRESS = PREFIX + "remoteAddress";
    
    public static final String USER_AGENT = PREFIX + "userAgent";

    private Context() {
        
    }
    
}

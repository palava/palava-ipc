package de.cosmocode.palava.ipc;

import java.net.InetAddress;

/**
 * 
 *
 * @since 1.9 
 * @author Willi Schoenborn
 * @author Tobias Sarnowski
 */
public interface Browser {

    String getHttpHost();
    
    boolean isHttps();
    
    String getRequestUri();
    
    String getRequestMethod();
    
    String getReferer();
    
    InetAddress getRemoteAddress();
    
    String getUserAgent();
    
    String getHttpAccept();
    
    String getHttpAcceptLanguage();
    
    String getHttpAcceptEncoding();
    
    String getHttpAcceptCharset();
    
}

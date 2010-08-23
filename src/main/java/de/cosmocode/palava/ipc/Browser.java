package de.cosmocode.palava.ipc;

import java.net.InetSocketAddress;

/**
 * 
 *
 * @since 
 * @author Willi Schoenborn
 */
public interface Browser {

    String getHttpHost();
    
    boolean isHttps();
    
    String getRequestUri();
    
    String getRequestMethod();
    
    String getReferer();
    
    InetSocketAddress getRemoteAddress();
    
    String getUserAgent();
    
    String getHttpAccept();
    
    String getHttpAcceptLanguage();
    
    String getHttpAcceptEncoding();
    
    String getHttpAcceptCharset();
    
}

package de.cosmocode.palava.ipc;

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
    
    String getRemoteAddress();
    
    String getUserAgent();
    
    String getHttpAccept();
    
    String getHttpAcceptLanguage();
    
    String getHttpAcceptEncoding();
    
    String getHttpAcceptCharset();
    
}

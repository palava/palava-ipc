/**
 * Copyright 2010 CosmoCode GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.cosmocode.palava.ipc;

/**
 * Allows access to some browser features. 
 *
 * @since 1.9 
 * @author Willi Schoenborn
 * @author Tobias Sarnowski
 */
public interface Browser {

    /**
     * Provides the http host.
     * 
     * @since 1.9
     * @return the http host
     * @throws UnsupportedOperationException if this information is not available
     */
    String getHttpHost();
    
    /**
     * Provides the https status.
     * 
     * @since 1.9
     * @return true if https is enabled, false otherwise
     * @throws UnsupportedOperationException if this information is not available
     */
    boolean isHttps();
    
    /**
     * Provides the request uri.
     * 
     * @since 1.9
     * @return the request uri
     * @throws UnsupportedOperationException if this information is not available
     */
    String getRequestUri();
    
    /**
     * Provides the request method.
     * 
     * @since 1.9
     * @return the request method
     * @throws UnsupportedOperationException if this information is not available
     */
    String getRequestMethod();
    
    /**
     * Provides the referer.
     * 
     * @since 1.9
     * @return the referer
     * @throws UnsupportedOperationException if this information is not available
     */
    String getReferer();
    
    /**
     * Provides the remote address.
     * 
     * @since 1.9
     * @return the remote address
     * @throws UnsupportedOperationException if this information is not available
     */
    String getRemoteAddress();
    
    /**
     * Provides the user agent.
     * 
     * @since 1.9
     * @return the user agent
     * @throws UnsupportedOperationException if this information is not available
     */
    String getUserAgent();
    
    /**
     * Provides the http accept header.
     * 
     * @since 1.9
     * @return the http accept header
     * @throws UnsupportedOperationException if this information is not available
     */
    String getHttpAccept();
    
    /**
     * Provides the http accept language header.
     * 
     * @since 1.9
     * @return the http accept language header
     * @throws UnsupportedOperationException if this information is not available
     */
    String getHttpAcceptLanguage();
    
    /**
     * Provides the http accept encoding header.
     * 
     * @since 1.9
     * @return the http accept encoding header
     * @throws UnsupportedOperationException if this information is not available
     */
    String getHttpAcceptEncoding();
    
    /**
     * Provides the http accept charset header.
     * 
     * @since 1.9
     * @return the http accept charset header
     * @throws UnsupportedOperationException if this information is not available
     */
    String getHttpAcceptCharset();
    
}

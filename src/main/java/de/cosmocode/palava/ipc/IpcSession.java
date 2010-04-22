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

import java.util.Date;
import java.util.concurrent.TimeUnit;

import de.cosmocode.palava.scope.ScopeContext;

/**
 * A session can hold informations between multiple requests
 * and is tide to a client or person.
 *
 * @author Tobias Sarnowski
 * @author Willi Schoenborn
 */
public interface IpcSession extends ScopeContext {

    /**
     * Provide the session id of this session.
     *
     * @return the session id
     */
    String getSessionId();
    
    /**
     * Provide the session's identifier, might be null.
     * 
     * @return the identifier of this session
     */
    String getIdentifier();
    
    /**
     * Provides the creation timestamp.
     * 
     * @return the date when this session was created
     */
    Date startedAt();
    
    /**
     * Provides the last access time.
     * 
     * @return the date of last access
     */
    Date lastAccessTime();
    
    /**
     * Touches this session. This results in an update
     * of the last access time as provided by {@link IpcSession#lastAccessTime()}.
     */
    void touch();

    /**
     * Gets the timeout of this session.
     * 
     * @param unit the desired time unit 
     * @return the timeout of this session in the specified time unit
     * @throws NullPointerException if unit is null
     */
    long getTimeout(TimeUnit unit);
    
    /**
     * Sets the timeout of this session.
     * 
     * @param timeout the new timeout value
     * @param unit the time unit of the specified timeout value
     * @throws NullPointerException if unit is null
     */
    void setTimeout(long timeout, TimeUnit unit);
    
    /**
     * Checks whether this session is expired. A session
     * is considered expired if the time since the last touch
     * is greater than the configured timeout.
     * 
     * @return true if this session is expired, false otherwise
     */
    boolean isExpired();

}

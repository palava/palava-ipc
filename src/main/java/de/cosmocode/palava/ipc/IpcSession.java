/**
 * palava - a java-php-bridge
 * Copyright (C) 2007-2010  CosmoCode GmbH
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package de.cosmocode.palava.ipc;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * A session can hold informations between multiple requests
 * and is tide to a client or person.
 *
 * @author Tobias Sarnowski
 * @author Willi Schoenborn
 */
public interface IpcSession extends IpcScopeContext {

    /**
     * Provide the session id of this session.
     *
     * @return the session id
     */
    String getSessionId();
    
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
     * Destroys this session. Any exception occuring
     * will be suppressed.
     */
    void destroy();
    
}

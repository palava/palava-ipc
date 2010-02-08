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

/**
 * Should be thrown if something went wrong during the
 * execution of a {@link IpcCommand}.
 *
 * @author Tobias Sarnowski
 */
public class IpcCommandExecutionException extends Exception {

    private static final long serialVersionUID = 8749819392899952996L;

    /**
     * This is object is just a wrapper and as a consequence,
     * you can only nest other exceptions.
     *
     * @param cause the real exception
     */
    public IpcCommandExecutionException(Throwable cause) {
        super(cause);
    }

}
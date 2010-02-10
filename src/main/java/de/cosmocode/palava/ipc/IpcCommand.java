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

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

/**
 * A command provides an interface to get called by
 * a heterogeneous environment.
 *
 * @author Tobias Sarnowski
 */
public interface IpcCommand {

    /**
     * This method will be called upon request.
     *
     * @param ipcCall Contains all given informations.
     * @param result Can be filled with return values.
     * @throws IpcCommandExecutionException if execution failed due to an exception
     */
    void execute(IpcCall ipcCall, Map<String, Object> result) throws IpcCommandExecutionException;


    /**
     * Has a description for the implemented {@link IpcCommand}.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Description {
        String value();
    }

    /**
     * List of all parameters.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Params {
        Param[] value() default { };
    }

    /**
     * Specifies a single parameter.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Param {
        String name();
        String description() default "";
        boolean optional() default false;
    }

    /**
     * List of all exceptions which can be thrown.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Throws {
        Throw[] value() default { };
    }

    /**
     * Specifies one exception which can be thrown.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Throw {
        Class<? extends Throwable> name();
        String description() default "";
    }

    /**
     * List of all result keys.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Returns {
        Return[] value() default { };
    }

    /**
     * A single results key and its meaning.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    public @interface Return {
        String name();
        String description() default "";
    }
}

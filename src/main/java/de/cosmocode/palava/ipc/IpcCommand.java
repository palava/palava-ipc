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
     * @param call Contains all given informations.
     * @param result Can be filled with return values.
     * @throws IpcCommandExecutionException if execution failed due to an exception
     */
    void execute(IpcCall call, Map<String, Object> result) throws IpcCommandExecutionException;

    /**
     * Marks MetaInformation annotations.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.ANNOTATION_TYPE)
    public @interface Meta {

    }


    /**
     * Has a description for the implemented {@link IpcCommand}.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Meta
    public @interface Description {

        String value();

    }

    /**
     * List of all parameters.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Meta
    public @interface Params {

        Param[] value();

    }

    /**
     * Specifies a single parameter.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Meta
    public @interface Param {

        String name();
        
        String type() default "";

        String description() default "";

        boolean optional() default false;

        String defaultValue() default "";

    }

    /**
     * List of all exceptions which can be thrown.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Meta
    public @interface Throws {

        Throw[] value();

    }

    /**
     * Specifies one exception which can be thrown.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Meta
    public @interface Throw {

        Class<? extends Throwable> name();

        String description() default "";

    }

    /**
     * List of all result keys.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Meta
    public @interface Returns {

        Return[] value();

    }

    /**
     * A single results key and its meaning.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Meta
    public @interface Return {

        String name();

        String description() default "";

    }
    
}

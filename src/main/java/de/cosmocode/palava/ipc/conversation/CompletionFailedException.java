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

package de.cosmocode.palava.ipc.conversation;

import java.util.Collections;
import java.util.Map;

import com.google.common.base.Functions;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import de.cosmocode.commons.Throwables;

/**
 * An exception containing execptions and their source objects.
 *
 * @since 1.4
 * @author Willi Schoenborn
 */
public final class CompletionFailedException extends Exception {

    private static final long serialVersionUID = -5181069031934071159L;
    
    private static final Joiner JOINER = Joiner.on("\n");
    
    private final Map<Throwable, Object> errors = Maps.newHashMap();
    
    public CompletionFailedException() {
        super();
    }

    public CompletionFailedException(Map<Throwable, Object> errors) {
        this.errors.putAll(errors);
    }

    /**
     * Adds the specified pair to this exception.
     * 
     * @since 1.4
     * @param throwable the occured throwable
     * @param object the object which has thrown the corresponding exception
     */
    void put(Throwable throwable, Object object) {
        errors.put(throwable, object);
    }
    
    public Map<Throwable, Object> getErrors() {
        return Collections.unmodifiableMap(errors);
    }
    
    @Override
    public String getMessage() {
        return JOINER.join(Iterables.transform(errors.keySet(), Throwables.getMessage()));
    }
    
    @Override
    public String toString() {
        return String.format("%s:\n%s", 
            this, JOINER.join(Iterables.transform(errors.keySet(), Functions.toStringFunction()))
        );
    }
    
}

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

import java.util.List;

import com.google.common.base.Predicate;
import com.google.inject.Key;
import com.google.inject.TypeLiteral;

/**
 * A filter definition contains a predicate matching commands
 * and a key addressing a filter binding in guice.
 *
 * @author Willi Schoenborn
 */
interface IpcCallFilterDefinition {

    TypeLiteral<List<IpcCallFilterDefinition>> LITERAL = new TypeLiteral<List<IpcCallFilterDefinition>>() { };
    
    /**
     * Provides the matcher predicate.
     * 
     * @return the predicate used to match commands
     */
    Predicate<? super IpcCommand> getPredicate();
    
    /**
     * Provides the guice key for the associated filter.
     * 
     * @return the filter binding key
     */
    Key<? extends IpcCallFilter> getKey();
    
}

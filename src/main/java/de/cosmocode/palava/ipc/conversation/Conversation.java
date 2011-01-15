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

import java.util.concurrent.ConcurrentMap;

import de.cosmocode.palava.scope.Destroyable;

/**
 * A conversation is similiar to a scope and may
 * span multiple connections but is less than
 * one session.
 *
 * @since 1.4
 * @author Willi Schoenborn
 */
public interface Conversation extends ConcurrentMap<Object, Object>, Destroyable {

    /**
     * Aborts this conversation and calls {@link Destroyable#destroy()}
     * on all {@link Destroyable} keys and values stored in this conversation.
     * <p>
     *   This method tries to call all {@link Destroyable}s, even in case of an error.
     * </p>
     * 
     * @since 1.4
     */
    void abort();
    
    /**
     * Ends this conversation and calls {@link Completable#complete()}
     * on all {@link Completable} keys and values stored in this conversation.
     * <p>
     *   This method tries to call all {@link Completable}s, even in case of an error.
     * </p>
     * 
     * @since 1.4
     * @throws CompletionFailedException if any key/value completion failed
     */
    void end() throws CompletionFailedException;
    
}

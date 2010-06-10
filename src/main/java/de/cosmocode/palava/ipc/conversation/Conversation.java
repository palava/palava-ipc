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

import java.util.Map;

import de.cosmocode.palava.scope.Destroyable;

/**
 * A conversation is similiar to a scope and may
 * span multiple connections but is less than
 * one session.
 *
 * @since 1.4
 * @author Willi Schoenborn
 */
public interface Conversation extends Destroyable {

    /**
     * Binds a key to a specific value.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param key the key
     * @param value the value
     * @throws NullPointerException if key is null
     */
    <K, V> void set(K key, V value);

    /**
     * Retrieves a value bound to the specified key.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param key the key
     * @return the value bound to the key or null if there
     *         is no value for the given key
     * @throws NullPointerException if key is null
     */
    <K, V> V get(K key);

    /**
     * Checks for the existence of a binding
     * for the specified key.
     *
     * @param <K> the key type
     * @param key the key
     * @return true if the key is currently bound to a value
     * @throws NullPointerException if key is null
     */
    <K> boolean contains(K key);

    /**
     * Removes a key-value binding.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param key the key
     * @return the old value if the specified key was bound to a
     *         value before, null otherwise
     * @throws NullPointerException if key is null
     */
    <K, V> V remove(K key);

    /**
     * Puts all elements of the specified in this conversation.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param map the map providing key-value bindings
     * @throws NullPointerException if map is null
     */
    <K, V> void putAll(Map<? extends K, ? extends V> map);

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

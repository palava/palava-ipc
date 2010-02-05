package de.cosmocode.palava.ipc;

import java.util.Map;

/**
 * A session can hold informations between multiple requests
 * and is tide to a client or person.
 *
 * @author Tobias Sarnowski
 */
public interface Session {
	/**
     * Provide the session id of this session.
     *
     * @return the session id
     */
    String getSessionId();

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
     */
    <K, V> V get(K key);

    /**
     * Checks for the existence of a binding
     * for the specified key.
     *
     * @param <K> the key type
     * @param key the key
     * @return true if the key is currently bound to a value
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
     */
    <K, V> V remove(K key);

    /**
     * Puts all elements of the specified in this session.
     *
     * @param <K> the key type
     * @param <V> the value type
     * @param map the map providing key-value bindings
     * @throws NullPointerException if map is null
     */
    <K, V> void putAll(Map<? extends K, ? extends V> map);

}

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
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Preconditions;

import de.cosmocode.palava.scope.AbstractScopeContext;

/**
 * Abstract implementation of the {@link IpcSession} interface.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractIpcSession extends AbstractScopeContext implements IpcSession {

    private long timeout;

    private TimeUnit timeoutUnit;

    private Date startedAt = new Date();

    private long lastAccess = System.currentTimeMillis();

    private boolean suppressingTouch;

    protected void setSuppressingTouch(boolean suppressingTouch) {
        this.suppressingTouch = suppressingTouch;
    }

    protected void setLastAccess(Date lastAccess) {
        this.lastAccess = Preconditions.checkNotNull(lastAccess, "LastAccess").getTime();
    }

    @Override
    public long getTimeout(TimeUnit unit) {
        Preconditions.checkNotNull(unit, "Unit");
        return unit.convert(timeout, timeoutUnit);
    }

    @Override
    public Date lastAccessTime() {
        return new Date(lastAccess);
    }

    @Override
    public void setTimeout(long time, TimeUnit unit) {
        Preconditions.checkNotNull(unit, "Unit");
        this.timeout = time;
        this.timeoutUnit = unit;
    }

    @Override
    public Date startedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = Preconditions.checkNotNull(startedAt, "startedAt");
    }

    @Override
    public void touch() {
        if (suppressingTouch) return;
        this.lastAccess = System.currentTimeMillis();
    }

    @Override
    public boolean isExpired() {
        return (System.currentTimeMillis() - lastAccess) > timeoutUnit.toMillis(timeout);
    }

    @Override
    public <K, V> void set(K key, V value) {
        touch();
        super.set(key, value);
    }

    @Override
    public <K, V> V get(K key) {
        touch();
        return super.<K, V>get(key);
    }

    @Override
    public <K> boolean contains(K key) {
        touch();
        return super.contains(key);
    }

    @Override
    public <K, V> V remove(K key) {
        touch();
        return super.<K, V>remove(key);
    }

    @Override
    public <K, V> void putAll(Map<? extends K, ? extends V> map) {
        touch();
        super.putAll(map);
    }

    @Override
    public Iterator<Entry<Object, Object>> iterator() {
        touch();
        final Iterator<Entry<Object, Object>> delegate = super.iterator();
        return new Iterator<Entry<Object, Object>>() {

            @Override
            public boolean hasNext() {
                touch();
                return delegate.hasNext();
            }

            @Override
            public Entry<Object, Object> next() {
                touch();
                return delegate.next();
            }

            @Override
            public void remove() {
                touch();
                delegate.remove();
            }

        };
    }

}

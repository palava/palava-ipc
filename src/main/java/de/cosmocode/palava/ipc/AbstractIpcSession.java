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

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Preconditions;

import de.cosmocode.collections.callback.Callback;
import de.cosmocode.collections.callback.Callbacks;
import de.cosmocode.palava.scope.AbstractScopeContext;

/**
 * Abstract implementation of the {@link IpcSession} interface.
 *
 * @author Willi Schoenborn
 */
public abstract class AbstractIpcSession extends AbstractScopeContext implements IpcSession, Serializable {

    private static final long serialVersionUID = 6438707896566787757L;

    private long timeout;

    private TimeUnit timeoutUnit;

    private Date startedAt = new Date();

    private long lastAccess = System.currentTimeMillis();

    private boolean suppressingTouch;
    
    /**
     * {@link Callback} implementation used to handle updates in collection views.
     *
     * @since 2.0
     * @author Willi Schoenborn
     */
    private final class Toucher implements Callback {

        @Override
        public void poke() {
            touch();
        }
        
    }
    
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
        if (timeout == 0 || timeoutUnit == null) {
            return false;
        } else {
            return System.currentTimeMillis() - lastAccess > timeoutUnit.toMillis(timeout);
        }
    }

    @Override
    public Object putIfAbsent(Object key, Object value) {
        touch();
        return super.putIfAbsent(key, value);
    }

    @Override
    public boolean remove(Object key, Object value) {
        touch();
        return super.remove(key, value);
    }

    @Override
    public Object replace(Object key, Object value) {
        touch();
        return super.replace(key, value);
    }

    @Override
    public boolean replace(Object key, Object oldValue, Object newValue) {
        touch();
        return super.replace(key, oldValue, newValue);
    }

    @Override
    public int size() {
        touch();
        return super.size();
    }

    @Override
    public boolean isEmpty() {
        touch();
        return super.isEmpty();
    }

    @Override
    public Object remove(Object object) {
        touch();
        return super.remove(object);
    }

    @Override
    public void clear() {
        touch();
        super.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        touch();
        return super.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        touch();
        return super.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        touch();
        return super.get(key);
    }

    @Override
    public Object put(Object key, Object value) {
        touch();
        return super.put(key, value);
    }

    @Override
    public void putAll(Map<? extends Object, ? extends Object> map) {
        touch();
        super.putAll(map);
    }

    @Override
    public Set<Object> keySet() {
        touch();
        return Callbacks.compose(super.keySet(), new Toucher());
    }

    @Override
    public Collection<Object> values() {
        touch();
        return Callbacks.compose(super.values(), new Toucher());
    }

    @Override
    public Set<Entry<Object, Object>> entrySet() {
        touch();
        return Callbacks.compose(super.entrySet(), new Toucher());
    }

}

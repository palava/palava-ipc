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

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

public abstract class AbstractIpcSession extends AbstractIpcScopeContext implements IpcSession {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractIpcSession.class);

    private long timeout;
    
    private TimeUnit timeoutUnit;
    
    private final Date startedAt = new Date();
    
    private long lastAccess = System.currentTimeMillis();
    
    @Override
    public void destroy() {
        context().clear();
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

    @Override
    public void touch() {
        this.lastAccess = System.currentTimeMillis();
    }

    @Override
    public <K> boolean contains(K key) {
        touch();
        return super.contains(key);
    }

    @Override
    public <K, V> V get(K key) {
        touch();
        return super.<K, V>get(key);
    }

    @Override
    public Iterator<Entry<Object, Object>> iterator() {
        touch();
        return super.iterator();
    }

    @Override
    public <K, V> void putAll(Map<? extends K, ? extends V> map) {
        touch();
        super.putAll(map);
    }

    @Override
    public <K, V> V remove(K key) {
        touch();
        return super.<K, V>remove(key);
    }

    @Override
    public <K, V> void set(K key, V value) {
        touch();
        super.set(key, value);
    }

}

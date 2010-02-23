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
        return super.get(key);
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
        return super.remove(key);
    }

    @Override
    public <K, V> void set(K key, V value) {
        touch();
        super.set(key, value);
    }

}

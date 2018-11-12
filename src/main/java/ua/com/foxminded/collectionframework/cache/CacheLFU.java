package ua.com.foxminded.collectionframework.cache;

import java.util.Map;

public class CacheLFU<K, V> extends Cache<K, V> {

    @Override
    public V get(K key) {
        CacheValue<V> cacheValue = getStorage().get(key);
        int currentEvictionPriority = cacheValue.getEvictionPriority();
        cacheValue.setEvictionPriority(++currentEvictionPriority);
        return cacheValue.getValue();
    }

    @Override
    public void put(K key, V value) {
        Map<K, CacheValue<V>> storage = getStorage();
        if (storage.size() == getMaxSize()) {
            deleteMinPriorityItem();
        }
        CacheValue cacheValue = new CacheValue(value);
        storage.put(key, cacheValue);
    }
}

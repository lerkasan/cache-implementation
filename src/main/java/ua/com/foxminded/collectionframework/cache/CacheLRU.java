package ua.com.foxminded.collectionframework.cache;

import java.util.Map;

public class CacheLRU<K, V> extends Cache<K, V> {

    private int evictionPriorityCounter;

    public CacheLRU() {
        System.out.print("Creating LRU cache "); // TODO: Remove this debugging output
    }

    public CacheLRU(int cacheCapacity) {
        super(cacheCapacity);
        System.out.print("Creating LRU cache "); // TODO: Remove this debugging output
    }

    @Override
    public V get(K key) {
        CacheValue<V> cacheValue = getStorage().get(key);
        cacheValue.setEvictionPriority(++evictionPriorityCounter);
        return cacheValue.getValue();
    }

    @Override
    public void put(K key, V value) {
        Map<K, CacheValue<V>> storage = getStorage();
        if (storage.size() == getCapacity()) {
            deleteMinPriorityItem();
        }
        CacheValue cacheValue = new CacheValue(value);
        cacheValue.setEvictionPriority(++evictionPriorityCounter);
        storage.put(key, cacheValue);
    }
}

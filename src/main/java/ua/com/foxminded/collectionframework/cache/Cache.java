package ua.com.foxminded.collectionframework.cache;

import java.util.HashMap;
import java.util.Map;

public abstract class Cache<K, V> {

    protected static final String EMPTY_CACHE = "Cache is empty. Can't get element from empty cache.";

    private static final int DEFAULT_MAX_SIZE = 100;

    private int maxSize = DEFAULT_MAX_SIZE;
    private Map<K, CacheValue<V>> storage = new HashMap<>();

    public Cache() {
    }

    public Cache(int maxSize) {
        this.maxSize = maxSize;
    }

    protected Map<K, CacheValue<V>> getStorage() {
        return storage;
    }

    protected void setStorage(Map<K, CacheValue<V>> storage) {
        this.storage = storage;
    }

    protected int getMaxSize() {
        return maxSize;
    }

    public int size() {
        return storage.size();
    }

    public abstract void put(K key, V value);

    public abstract V get(K key);

    public void remove(K key) {
        storage.remove(key);
    }

    public void clear() {
        storage.clear();
    }

    public boolean containsKey(K key) {
        return storage.containsKey(key);
    }

    protected K getItemKeyWithMaxEvictionPriority() {
        int maxEvictionValue = 0;
        if (storage.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_CACHE);
        }
        K maxEvictionPriorityItemKey = (K) storage.keySet().toArray()[0];
        for (Map.Entry<K, CacheValue<V>> entry : storage.entrySet()) {
            if (entry.getValue().getEvictionPriority() >= maxEvictionValue) {
                maxEvictionPriorityItemKey = entry.getKey();
            }
        }
        return maxEvictionPriorityItemKey;
    }

    protected K getItemWithMinEvictionPriority() {
        if (storage.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_CACHE);
        }
        K minEvictionPriorityItemKey = (K) storage.keySet().toArray()[0];
        CacheValue<V> minEvictionPriorityItemValue = storage.get(minEvictionPriorityItemKey);
        for (Map.Entry<K, CacheValue<V>> entry : storage.entrySet()) {
            CacheValue<V> cacheValue = entry.getValue();
            if (cacheValue.compareTo(minEvictionPriorityItemValue) <= 0) {
                minEvictionPriorityItemValue = cacheValue;
                minEvictionPriorityItemKey = entry.getKey();
            }
        }
        return minEvictionPriorityItemKey;
    }

    protected void deleteMinPriorityItem() {
        K minEvictionPriorityItemKey = getItemWithMinEvictionPriority();
        System.out.println("\n Removing minimal priority item from cache: " + minEvictionPriorityItemKey);   // TODO: Remove this debugging output
        remove(minEvictionPriorityItemKey);
    }

    @Override
    public String toString() {
        return storage.toString();
    }
}

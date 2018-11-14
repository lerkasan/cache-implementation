package ua.com.foxminded.collectionframework.cache;

import java.util.HashMap;
import java.util.Map;

public abstract class Cache<K, V> {

    public static final int DEFAULT_MAX_SIZE = 100;
    public static final String EMPTY_CACHE = "Cache is empty. Can't get an element from the empty cache.";
    public static final String INVALID_CACHE_SIZE = "Cache capacity should be in a range from 1 to " + DEFAULT_MAX_SIZE + ". You entered invalid cache capacity ";

    private int capacity = DEFAULT_MAX_SIZE;

    private Map<K, CacheValue<V>> storage = new HashMap<>();

    public Cache() {
    }

    public Cache(int capacity) {
        if ((capacity <= 0) || (capacity > DEFAULT_MAX_SIZE)) {
            throw new IllegalArgumentException(INVALID_CACHE_SIZE + capacity);
        }
        this.capacity = capacity;
    }

    protected Map<K, CacheValue<V>> getStorage() {
        return storage;
    }

    protected int getCapacity() {
        return capacity;
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

    protected K getItemKeyWithMinEvictionPriority() {
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
        K minEvictionPriorityItemKey = getItemKeyWithMinEvictionPriority();
        remove(minEvictionPriorityItemKey);
    }

    @Override
    public String toString() {
        return storage.toString();
    }
}

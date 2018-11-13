package ua.com.foxminded.collectionframework.cache;

public class CacheFactory {

    private static final String NULL_TYPE_MESSAGE = "Cache eviction policy type shouldn't be null. ";
    private static final String ILLEGAL_TYPE_MESSAGE = "Illegal cache eviction policy type: ";

    private static final String DEFAULT_CACHE_TYPE = "LRU";

    private CacheFactory() {
    }

    public static Cache getCache() {
        return initCache(DEFAULT_CACHE_TYPE);
    }

    public static Cache getCache(String type) {
        return initCache(type);
    }

    public static Cache getCache(int cacheCapacity) {
        return initCache(DEFAULT_CACHE_TYPE, cacheCapacity);
    }
    
    public static Cache getCache(String type, int cacheCapacity) {
        return initCache(type, cacheCapacity);
    }

    private static Cache initCache(String type, int... cacheCapacity) {
        if (type == null) {
            throw new IllegalArgumentException(NULL_TYPE_MESSAGE);
        }
        type = type.toLowerCase();
        switch (type) {
            case "lru":
                return cacheCapacity.length == 0 ? new CacheLRU() : new CacheLRU(cacheCapacity[0]);
            case "lfu":
                return cacheCapacity.length == 0 ? new CacheLFU() : new CacheLFU(cacheCapacity[0]);
            default:
                throw new IllegalArgumentException(ILLEGAL_TYPE_MESSAGE + type);
        }
    }
}

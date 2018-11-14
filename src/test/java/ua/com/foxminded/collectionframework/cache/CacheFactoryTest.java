package ua.com.foxminded.collectionframework.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CacheFactoryTest {

    @Test
    void shouldReturnCacheWithDefaultCapacityAndDefaultEvictionPolicy() {
        Cache cache = CacheFactory.getCache();
        int actualCapacity =  cache.getCapacity();
        int expectedCapacity = Cache.DEFAULT_MAX_SIZE;
        Class actualType = cache.getClass();
        Class expectedType = CacheLRU.class;

        assertEquals(expectedCapacity, actualCapacity);
        assertEquals(expectedType, actualType);
    }

    @Test
    void shouldReturnCacheWithDefinedCapacityAndDefaultEvictionPolicy() {
        int expectedCapacity = 25;
        Cache cache = CacheFactory.getCache(expectedCapacity);
        int actualCapacity =  cache.getCapacity();
        Class actualType = cache.getClass();
        Class expectedType = CacheLRU.class;

        assertEquals(expectedCapacity, actualCapacity);
        assertEquals(expectedType, actualType);
    }

    @Test
    void shouldReturnCacheWithDefaultCapacityAndDefinedEvictionPolicy() {
        Cache cache = CacheFactory.getCache("LFU");
        int expectedCapacity = Cache.DEFAULT_MAX_SIZE;
        int actualCapacity =  cache.getCapacity();
        Class actualType = cache.getClass();
        Class expectedType = CacheLFU.class;

        assertEquals(expectedCapacity, actualCapacity);
        assertEquals(expectedType, actualType);
    }

    @Test
    void shouldReturnCacheWithDefinedCapacityAndDefinedEvictionPolicy() {
        int expectedCapacity = 42;
        String type = "LFU";
        Cache cache = CacheFactory.getCache(type, expectedCapacity);
        int actualCapacity =  cache.getCapacity();
        Class actualType = cache.getClass();
        Class expectedType = CacheLFU.class;

        assertEquals(expectedCapacity, actualCapacity);
        assertEquals(expectedType, actualType);
    }
}

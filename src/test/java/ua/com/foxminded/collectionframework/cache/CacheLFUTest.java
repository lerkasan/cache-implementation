package ua.com.foxminded.collectionframework.cache;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CacheLFUTest extends CacheLFU {

    private CacheLFU underTest;

    @BeforeEach
    void setUp() {
        int capacity = 5;
        underTest = new CacheLFU(capacity);
        underTest.put("a", "aaa");
        underTest.put("b", "bbb");
        underTest.put("c", "ccc");
        underTest.put("d", "abc");
        underTest.put("e", "cba");
    }

    @Test
    void shouldThrowExceptionWithNegativeCapacity() {
        int capacity = -45;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new CacheLFU(capacity));
        assertEquals(Cache.INVALID_CACHE_SIZE + capacity, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWitZeroCapacity() {
        int capacity = 0;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new CacheLFU(capacity));
        assertEquals(Cache.INVALID_CACHE_SIZE + capacity, exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWithMoreThanMaxCapacity() {
        int capacity = Cache.DEFAULT_MAX_SIZE + 1;
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new CacheLFU(capacity));
        assertEquals(Cache.INVALID_CACHE_SIZE + capacity, exception.getMessage());
    }

    @Test
    void shouldReturnMinPriorityItemKey() {
        underTest.get("a");
        underTest.get("a");
        underTest.get("a");
        underTest.get("a");
        underTest.get("b");
        underTest.get("c");
        underTest.get("b");
        underTest.get("a");
        underTest.get("d");
        underTest.get("c");
        underTest.get("c");
        underTest.get("b");
        underTest.get("c");
        underTest.get("e");
        String expected = "e"; // less frequently accessed item
        String actual = (String) underTest.getItemKeyWithMinEvictionPriority();
        assertEquals(expected, actual);
    }

    @Test
    void shouldDeleteMinPriorityItem() {
        for (int i = 0; i <= 15; i++) {
            underTest.get("a");
        }
        for (int i = 0; i <= 10; i++) {
            underTest.get("b");
        }
        for (int i = 0; i <= 5; i++) {
            underTest.get("c");
        }
        underTest.get("d");
        underTest.get("d");
        for (int i = 0; i <= 20; i++) {
            underTest.get("e");
        }
        String expected = "d"; // less frequently accessed item
        String actual = (String) underTest.getItemKeyWithMinEvictionPriority();
        assertEquals(expected, actual);

        assertTrue(underTest.containsKey(expected));
        underTest.deleteMinPriorityItem();
        assertFalse(underTest.containsKey(expected));
    }

    @Test
    void shouldIncrementEvictionPriorityOnGet() {
        String key = "a";
        CacheValue cacheValue = (CacheValue) underTest.getStorage().get(key);
        int oldPriority = cacheValue.getEvictionPriority();

        underTest.get(key);
        cacheValue = (CacheValue) underTest.getStorage().get(key);
        int newPriority = cacheValue.getEvictionPriority();

        int expected = 1;
        int actual = newPriority - oldPriority;
        assertEquals(expected, actual);
    }

    @Test
    void shouldReplaceLessPrioritizedItemOnPut() {
        underTest.get("d");
        for (int i = 0; i <= 15; i++) {
            underTest.get("a");
        }
        underTest.get("d");
        for (int i = 0; i <= 10; i++) {
            underTest.get("b");
        }
        for (int i = 0; i <= 5; i++) {
            underTest.get("c");
            underTest.get("d");
        }
        underTest.get("d");

        for (int i = 0; i <= 20; i++) {
            underTest.get("e");
        }
        underTest.get("c");

        String expected = "c"; // less frequently accessed item
        String actual = (String) underTest.getItemKeyWithMinEvictionPriority();
        assertEquals(expected, actual);

        String newItemKey = "f";
        String oldItemKey = expected;
        assertTrue(underTest.containsKey(oldItemKey));
        assertFalse(underTest.containsKey(newItemKey));

        underTest.put(newItemKey, "new item");
        assertFalse(underTest.containsKey(oldItemKey));
        assertTrue(underTest.containsKey(newItemKey));
    }
}

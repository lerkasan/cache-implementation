package ua.com.foxminded.collectionframework.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CacheValueTest {

    @Test
    void shouldCompareCacheValuesByPriority() {
        CacheValue firstCacheValue = new CacheValue(156);
        firstCacheValue.setEvictionPriority(15);

        CacheValue secondCacheValue = new CacheValue(-4);
        secondCacheValue.setEvictionPriority(62);

        boolean isFirstCacheValueBiggerByPriority = firstCacheValue.compareTo(secondCacheValue) > 0;
        assertFalse(isFirstCacheValueBiggerByPriority);

        boolean isFirstCacheValueSmallerByPriority = firstCacheValue.compareTo(secondCacheValue) < 0;
        assertTrue(isFirstCacheValueSmallerByPriority);

        boolean isSecondCacheValueBiggerByPriority = secondCacheValue.compareTo(firstCacheValue) > 0;
        assertTrue(isSecondCacheValueBiggerByPriority);

        boolean isSecondCacheValueSmallerByPriority = secondCacheValue.compareTo(firstCacheValue) < 0;
        assertFalse(isSecondCacheValueSmallerByPriority);
    }

    @Test
    void shouldCompareCacheValuesByPriority2() {
        CacheValue firstCacheValue = new CacheValue(12);
        firstCacheValue.setEvictionPriority(85);

        CacheValue secondCacheValue = new CacheValue(450);
        secondCacheValue.setEvictionPriority(3);

        boolean isFirstCacheValueBiggerByPriority = firstCacheValue.compareTo(secondCacheValue) > 0;
        assertTrue(isFirstCacheValueBiggerByPriority);

        boolean isFirstCacheValueSmallerByPriority = firstCacheValue.compareTo(secondCacheValue) < 0;
        assertFalse(isFirstCacheValueSmallerByPriority);

        boolean isSecondCacheValueBiggerByPriority = secondCacheValue.compareTo(firstCacheValue) > 0;
        assertFalse(isSecondCacheValueBiggerByPriority);

        boolean isSecondCacheValueSmallerByPriority = secondCacheValue.compareTo(firstCacheValue) < 0;
        assertTrue(isSecondCacheValueSmallerByPriority);
    }
}

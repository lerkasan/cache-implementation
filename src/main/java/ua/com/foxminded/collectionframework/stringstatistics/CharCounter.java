package ua.com.foxminded.collectionframework.stringstatistics;

import ua.com.foxminded.collectionframework.cache.Cache;
import ua.com.foxminded.collectionframework.cache.CacheFactory;

import java.util.HashMap;
import java.util.Map;

public class CharCounter {

    public static final String NULL_ARGUMENT = "String argument is null. Can't calculate char occurrences in a not-initialized string.";

    private Cache cache;

    public CharCounter() {
        cache = CacheFactory.getCache();
    }

    public CharCounter(Cache cache) {
        this.cache = cache;
    }

    protected Cache getCache() {
        return cache;
    }

    protected void setCache(Cache cache) {
        this.cache = cache;
    }

    protected Map<Character, Integer> calculateCharOccurrences(String input) {
        if (input == null) {
            throw new IllegalArgumentException(NULL_ARGUMENT);
        }
        char[] chars = input.toCharArray();
        Map<Character, Integer> charOccurrences = new HashMap<>();
        for (char character : chars) {
            int charAmount = charOccurrences.getOrDefault(character, 0);
            charOccurrences.put(character, charAmount + 1);
        }
        cache.put(input, charOccurrences);
        return charOccurrences;
    }

    public Map<Character, Integer> getCharOccurrences(String input) {
        if (cache.containsKey(input)) {
            return (Map<Character, Integer>) cache.get(input);
        }
        Map<Character, Integer> charOccurrences = calculateCharOccurrences(input);
        return charOccurrences;
    }
}

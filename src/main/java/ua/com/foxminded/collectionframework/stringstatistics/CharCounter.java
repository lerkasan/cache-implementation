package ua.com.foxminded.collectionframework.stringstatistics;

import ua.com.foxminded.collectionframework.cache.Cache;
import ua.com.foxminded.collectionframework.cache.CacheLRU;

import java.util.HashMap;
import java.util.Map;

public class CharCounter {

    private static final String NULL_ARGUMENT = "";

    private Cache cache = new CacheLRU<String, Map<Character, Integer>>(); // TODO: Use CacheFactory :)

    private Map<Character, Integer> calculateCharOccurrences(String input) {
        if (input == null) {
            throw new IllegalArgumentException(NULL_ARGUMENT);
        }
        char[] chars = input.toCharArray();
        Map<Character, Integer> charOccurrences = new HashMap<>();
        for (char character : chars) {
            int charAmount = charOccurrences.getOrDefault(character, 0);
            charOccurrences.put(character, charAmount + 1);
        }
        System.out.print("\nCalculating result for string: " + input);
        return charOccurrences;
    }

    public Map<Character, Integer> getCharOccurrences(String input) {
        if (cache.containsKey(input)) {
            System.out.print("\nGetting from cache result for string: " + input);
            return (Map<Character, Integer>) cache.get(input);
        }
        Map<Character, Integer> charOccurrences = calculateCharOccurrences(input);
        cache.put(input, charOccurrences);
        return charOccurrences;
    }

    public void printCharOccurrences(String input) {
        Map<Character, Integer> charOccurrences = getCharOccurrences(input);
        System.out.println("\nChar occurrences:\n" + input + "  " + charOccurrences);
    }

    public void printCacheContent() {   // TODO: Delete this debugging output method
        System.out.println("\nCache content:\n" + cache.toString());
    }
}

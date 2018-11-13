package ua.com.foxminded.collectionframework.stringstatistics;

import ua.com.foxminded.collectionframework.cache.Cache;
import ua.com.foxminded.collectionframework.cache.CacheFactory;

import java.util.HashMap;
import java.util.Map;

public class CharCounter {

    private static final String NULL_ARGUMENT = "";

    private Cache cache;

    public CharCounter() {
        cache = CacheFactory.getCache();
    }

    public CharCounter(Cache cache) {
        this.cache = cache;
    }

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
        System.out.print("\nCalculating result for string: " + input); // TODO: Delete this debugging output method
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

    public String formatCharOccurrences(String input) {
        //        System.out.println("\nChar occurrences:\n" + input + "  " + charOccurrences);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        Map<Character, Integer> charOccurrences = getCharOccurrences(input);
        for (Map.Entry<Character, Integer> entry : charOccurrences.entrySet()) {
            stringBuilder.append("\"" + entry.getKey() + "\" - " + entry.getValue() + "\n");
        }
        return stringBuilder.toString();
    }

    public void printCacheContent() {   // TODO: Delete this debugging output method
        System.out.println("\nCache content:\n" + cache.toString());
    }
}

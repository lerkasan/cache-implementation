package ua.com.foxminded.collectionframework;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class CharCounter {

    private static final String NULL_ARGUMENT = "";

    private Map<String, Map<Character, Integer>> cache = new WeakHashMap<>();

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
        System.out.print("\nCalculating...");
        return charOccurrences;
    }

    public Map<Character, Integer> getCharOccurrences(String input) {
        if (cache.containsKey(input)) {
            System.out.print("\nGetting from cache...");
            return cache.get(input);
        }
        Map<Character, Integer> charOccurrences = calculateCharOccurrences(input);
        cache.put(input, charOccurrences);
        return charOccurrences;
    }

    public void printCharOccurrences(String input) {
        Map<Character, Integer> charOccurrences = getCharOccurrences(input);
        System.out.println("\n" + input + "  " + charOccurrences);
    }
}

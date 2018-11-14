package ua.com.foxminded.collectionframework;

import ua.com.foxminded.collectionframework.cache.Cache;
import ua.com.foxminded.collectionframework.cache.CacheFactory;
import ua.com.foxminded.collectionframework.formatter.Formatter;
import ua.com.foxminded.collectionframework.stringstatistics.CharCounter;

import java.util.Scanner;

public class Main {
    public static final String EXIT_CMD = "exit";
    public static final String INVALID_CAPACITY = "Invalid capacity argument. Cache capacity should be a number.";
    public static final String INVALID_ARGUMENTS = "Invalid or too many commandline arguments. ";
    public static final String DEFAULT_CACHE_CREATED = "\nThe program created a cache with default capacity and eviction policy.";
    public static final String INTERACTIVE_REQUEST_FOR_INPUT = "\nEnter a string: ";
    public static final String INTRODUCTION_APP_INFO = "\nCalculating character occurrences in a string. Print 'exit' or press Ctrl + C or Ctrl + D to quit.\n" +
            "INFO: This program supports two optional commandline arguments: "
            + "\n-cacheType=type          - for choosing cache eviction policy from two options \"LRU\" or \"LFU\" "
            + "\n-cacheCapacity=number    - for choosing max capacity of cache"
            + "\nOther commandline arguments are ignored.";

    private static String getCommandLineArgumentValue(String argumentName, String[] arguments) {
        String argumentValue = "";
        String argumentOption = "-" + argumentName + "=";
        for (String arg : arguments) {
            if (arg.startsWith(argumentOption)) {
                argumentValue = arg.replace(argumentOption, "");
            }
        }
        return argumentValue;
    }

    private static Cache getCache(String[] args) {
        String cacheType;
        String cacheCapacity;
        int cacheCapacityValue = 0;
        switch (args.length) {
            case 0:
                return CacheFactory.getCache();
            case 1:
            case 2:
                cacheType = getCommandLineArgumentValue("cacheType", args);
                cacheCapacity = getCommandLineArgumentValue("cacheCapacity", args);
                if (!"".equals(cacheCapacity)) {
                    try {
                        cacheCapacityValue = Integer.valueOf(cacheCapacity);
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException(INVALID_CAPACITY);
                    }
                }
                if ((!"".equals(cacheType)) && (!"".equals(cacheCapacity))) {
                    return CacheFactory.getCache(cacheType, cacheCapacityValue);
                }
                if (!"".equals(cacheCapacity)) {
                    return CacheFactory.getCache(cacheCapacityValue);
                }
                if (!"".equals(cacheType)) {
                    return CacheFactory.getCache(cacheType);
                } // no break should be here to allow cacheType and cacheCapacity parameters that are non-compliant with previous checks to reach default section and throw exception
            default:
                throw new IllegalArgumentException(INVALID_ARGUMENTS);
        }
    }

    public static void main(String[] args) {
        String input;
        CharCounter charCounter;
        try {
            if (args.length == 0) {
                charCounter = new CharCounter();
            } else {
                Cache cache = getCache(args);
                charCounter = new CharCounter(cache);
            }
        } catch (IllegalArgumentException e) {
            charCounter = new CharCounter();
            System.out.println(e.getMessage() + DEFAULT_CACHE_CREATED);
        }
        Scanner in = new Scanner(System.in);
        System.out.println(INTRODUCTION_APP_INFO);
        do {
            System.out.print(INTERACTIVE_REQUEST_FOR_INPUT);
            input = in.nextLine();
            if (!EXIT_CMD.equals(input)) {
                try {
                    String result = Formatter.formatMapAsColumn(charCounter.getCharOccurrences(input));
                    System.out.println(result);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        } while (!EXIT_CMD.equalsIgnoreCase(input));
    }
}

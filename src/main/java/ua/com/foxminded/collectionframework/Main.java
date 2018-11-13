package ua.com.foxminded.collectionframework;

import ua.com.foxminded.collectionframework.cache.Cache;
import ua.com.foxminded.collectionframework.cache.CacheFactory;
import ua.com.foxminded.collectionframework.formatter.Formatter;
import ua.com.foxminded.collectionframework.stringstatistics.CharCounter;

public class Main {
    public static final String INVALID_CAPACITY = "Invalid capacity argument. Cache capacity should be a number.";
    public static final String INVALID_ARGUMENTS = "Invalid or too many commandline arguments. Program supports up to two optional commandline arguments: "
            + "\n-cacheType=type          - for choosing cache eviction policy from two options \"lru\" or \"lfu\" "
            + "\n-cacheCapacity=number    - for choosing max capacity of cache";

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
        Integer cacheCapacityValue = 0;
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
                } // no break should be here to allow cacheType and cacheCapacity parameters that are non-compliant with previous checks to get in default section and throw exception
            default:
                throw new IllegalArgumentException(INVALID_ARGUMENTS);
        }
    }

    public static void main(String[] args) {
        CharCounter charCounter;
        try {
            if (args.length == 0) {
                charCounter = new CharCounter();
            } else {
                Cache cache = getCache(args);
                charCounter = new CharCounter(cache);
            }

            String result = Formatter.formatMapAsColumn(charCounter.getCharOccurrences("hello world!"));
            System.out.println(result);
            charCounter.printCacheContent();


            result = Formatter.formatMapAsColumn(charCounter.getCharOccurrences("Life is wonderful!!!"));
            System.out.println(result);
            charCounter.printCacheContent();

            result = Formatter.formatMapAsColumn(charCounter.getCharOccurrences("hello world!"));
            System.out.println(result);
            charCounter.printCacheContent();

            result = Formatter.formatMapAsColumn(charCounter.getCharOccurrences("hello world!"));
            System.out.println(result);
            charCounter.printCacheContent();

            result = Formatter.formatMapAsColumn(charCounter.getCharOccurrences("Life is wonderful!!!"));
            System.out.println(result);
            charCounter.printCacheContent();

            result = Formatter.formatMapAsColumn(charCounter.getCharOccurrences("How are you?"));
            System.out.println(result);
            charCounter.printCacheContent();

            result = Formatter.formatMapAsColumn(charCounter.getCharOccurrences("Hello Java :)"));
            System.out.println(result);
            charCounter.printCacheContent();

            result = Formatter.formatMapAsColumn(charCounter.getCharOccurrences("Hello Java :)"));
            System.out.println(result);
            charCounter.printCacheContent();

            result = Formatter.formatMapAsColumn(charCounter.getCharOccurrences("Akukaracha!!!"));
            System.out.println(result);
            charCounter.printCacheContent();

            result = Formatter.formatMapAsColumn(charCounter.getCharOccurrences("This is a test."));
            System.out.println(result);
            charCounter.printCacheContent();

            result = Formatter.formatMapAsColumn(charCounter.getCharOccurrences("Akukaracha!!!"));
            System.out.println(result);
            charCounter.printCacheContent();

            result = Formatter.formatMapAsColumn(charCounter.getCharOccurrences("hello world!"));
            System.out.println(result);
            charCounter.printCacheContent();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}

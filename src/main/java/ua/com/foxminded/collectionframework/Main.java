package ua.com.foxminded.collectionframework;

import ua.com.foxminded.collectionframework.stringstatistics.CharCounter;

public class Main {

    public static void main(String[] args) {
        CharCounter charCounter = new CharCounter();

        charCounter.printCharOccurrences("hello world!");
        charCounter.printCacheContent();

        charCounter.printCharOccurrences("Life is wonderful!!!");
        charCounter.printCacheContent();

        charCounter.printCharOccurrences("hello world!");
        charCounter.printCacheContent();

        charCounter.printCharOccurrences("hello world!");
        charCounter.printCacheContent();

        charCounter.printCharOccurrences("Life is wonderful!!!");
        charCounter.printCacheContent();

        charCounter.printCharOccurrences("Good night");
        charCounter.printCacheContent();

        charCounter.printCharOccurrences("Hello Java :)");
        charCounter.printCacheContent();

        charCounter.printCharOccurrences("Hello Java :)");
        charCounter.printCacheContent();

        charCounter.printCharOccurrences("Akukaracha!!");
        charCounter.printCacheContent();

        charCounter.printCharOccurrences("This is test");
        charCounter.printCacheContent();

        charCounter.printCharOccurrences("Akukaracha!!");
        charCounter.printCacheContent();

        charCounter.printCharOccurrences("hello world!");
        charCounter.printCacheContent();
    }
}

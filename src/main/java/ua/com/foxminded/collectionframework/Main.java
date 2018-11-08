package ua.com.foxminded.collectionframework;

public class Main {

    public static void main(String[] args) {
        CharCounter charCounter = new CharCounter();
        charCounter.printCharOccurrences("hello world!");
        charCounter.printCharOccurrences("Life is wonderful!!!");
        charCounter.printCharOccurrences("hello world!");
    }
}

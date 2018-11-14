package ua.com.foxminded.collectionframework.stringstatistics;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ua.com.foxminded.collectionframework.cache.Cache;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CharCounterTest extends CharCounter {

    private CharCounter underTest;

    @BeforeEach
    void setUp() {
        underTest = new CharCounter();
    }

    @Test
    void shouldReturnCharOccurrencesForLowercaseString() {
        Map<Character, Integer> actual = underTest.getCharOccurrences("hello world!!!");
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('h', 1);
        expected.put('e', 1);
        expected.put('l', 3);
        expected.put('o', 2);
        expected.put(' ', 1);
        expected.put('w', 1);
        expected.put('r', 1);
        expected.put('d', 1);
        expected.put('!', 3);

        assertEquals(expected, actual);
    }

    @Test
    void shouldCountLowerAndUpperCaseCharsAsDifferent() {
        Map<Character, Integer> actual = underTest.getCharOccurrences("HeLLO to The WhOLE wOrld!!!");
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('h', 2);
        expected.put('H', 1);
        expected.put('e', 2);
        expected.put('E', 1);
        expected.put('l', 1);
        expected.put('L', 3);
        expected.put('o', 1);
        expected.put('O', 3);
        expected.put('t', 1);
        expected.put('T', 1);
        expected.put(' ', 4);
        expected.put('w', 1);
        expected.put('W', 1);
        expected.put('r', 1);
        expected.put('d', 1);
        expected.put('!', 3);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnCharOccurrencesForStringContainingOneRepeatingChar() {
        Map<Character, Integer> actual = underTest.getCharOccurrences("!!!!!!!");
        Map<Character, Integer> expected = new HashMap<>();
        expected.put('!', 7);

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnEmptyCharOccurrencesMapForEmptyString() {
        Map<Character, Integer> actual = underTest.getCharOccurrences("");
        Map<Character, Integer> expected = new HashMap<>();

        assertEquals(expected, actual);
    }

    @Test
    void shouldPutNewResultInCache() {
        String input = "hello world!!!";
        assertFalse(underTest.getCache().containsKey(input));

        Map<Character, Integer> actual = underTest.getCharOccurrences(input);
        assertTrue(underTest.getCache().containsKey(input));
    }

    @Test
    void shouldGetResultFromCache() {
        String input = "hello world!!!";
        Map<Character, Integer> charOccurrences = new HashMap<>();
        charOccurrences.put('h', 1);
        charOccurrences.put('e', 1);
        charOccurrences.put('l', 3);
        charOccurrences.put('o', 2);
        charOccurrences.put(' ', 1);
        charOccurrences.put('w', 1);
        charOccurrences.put('r', 1);
        charOccurrences.put('d', 1);
        charOccurrences.put('!', 3);

        Cache cacheMock = mock(Cache.class);
        when(cacheMock.containsKey(input)).thenReturn(true);
        when(cacheMock.get(input)).thenReturn(charOccurrences);

        underTest.setCache(cacheMock);
        underTest.getCharOccurrences(input);
        verify(cacheMock, times(1)).get(input);
    }

    @Test
    void shouldReturnCachedResult() {
        String input = "hello world!!!";
        Map<Character, Integer> charOccurrences = new HashMap<>();
        charOccurrences.put('h', 1);
        charOccurrences.put('e', 1);
        charOccurrences.put('l', 3);
        charOccurrences.put('o', 2);
        charOccurrences.put(' ', 1);
        charOccurrences.put('w', 1);
        charOccurrences.put('r', 1);
        charOccurrences.put('d', 1);
        charOccurrences.put('!', 3);

        CharCounter underTestSpy = spy(underTest);
        underTestSpy.getCharOccurrences(input);
        verify(underTestSpy, times(1)).calculateCharOccurrences(input); // calculation is invoked once here

        underTestSpy.getCharOccurrences(input);
        verify(underTestSpy, times(1)).calculateCharOccurrences(input); // no additional invocations of calculation
    }

    @Test
    void shouldThrowExceptionWithNullString() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> underTest.getCharOccurrences(null));
        assertEquals(CharCounter.NULL_ARGUMENT, exception.getMessage());
    }
}

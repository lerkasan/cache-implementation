package ua.com.foxminded.collectionframework.formatter;

import org.junit.jupiter.api.Test;
import ua.com.foxminded.collectionframework.stringstatistics.CharCounter;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FormatterTest {

    @Test
    void shouldFormatMapAsColumn() {
        CharCounter charCounter = new CharCounter();
        Map<Character, Integer> occurrences = charCounter.getCharOccurrences("hello world!!!");
        String actual = Formatter.formatMapAsColumn(occurrences);
        String expected = "\n" +
                "\" \" - 1\n" +
                "\"!\" - 3\n" +
                "\"r\" - 1\n" +
                "\"d\" - 1\n" +
                "\"e\" - 1\n" +
                "\"w\" - 1\n" +
                "\"h\" - 1\n" +
                "\"l\" - 3\n" +
                "\"o\" - 2\n";

        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowExceptionWithNullString() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> Formatter.formatMapAsColumn(null));
        assertEquals(Formatter.NULL_ARGUMENT, exception.getMessage());
    }
}

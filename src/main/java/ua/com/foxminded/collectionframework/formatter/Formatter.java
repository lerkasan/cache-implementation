package ua.com.foxminded.collectionframework.formatter;

import java.util.Map;

public class Formatter {

    public static final String NULL_ARGUMENT = "Map argument is null. Can't format null map.";

    private Formatter() {
    }

    public static String formatMapAsColumn(Map map) {
        if (map == null) {
            throw new IllegalArgumentException(NULL_ARGUMENT);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        map.forEach((k,v) -> stringBuilder.append("\"" + k + "\" - " + v + "\n"));
        return stringBuilder.toString();
    }
}

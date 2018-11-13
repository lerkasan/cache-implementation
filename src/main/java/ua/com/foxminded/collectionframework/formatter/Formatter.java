package ua.com.foxminded.collectionframework.formatter;

import java.util.Map;

public class Formatter {

    private Formatter() {
    }

    public static String formatMapAsColumn(Map map) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        map.forEach((k,v) -> stringBuilder.append("\"" + k + "\" - " + v + "\n"));
        return stringBuilder.toString();
    }
}

package me.timur.touroperatorbpa.util;

import java.util.List;

/**
 * Created by Temurbek Ismoilov on 02/08/23.
 */

public class StringUtil {
    public static List<String> split(String string, String delimiter) {
        return List.of(string.split(delimiter));
    }

    public String join(List<String> strings, String delimiter) {
        return String.join(delimiter, strings);
    }
}

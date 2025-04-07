package me.timur.touroperatorbpa.util;

import java.time.format.DateTimeFormatter;

/**
 * Created by Temurbek Ismoilov on 30/08/22.
 */

public class LocalDateTimeUtil {
    public final static String DATE_PATTERN = "dd-MM-yyyy";
    public final static String DATE_TIME_PATTERN = "dd-MM-yyyy HH:mm:ss";

    public static String toDay() {
        return java.time.LocalDate.now().format(DateTimeFormatter.ofPattern(DATE_PATTERN));
    }
}

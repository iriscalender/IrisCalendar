package com.dsm.iriscalendar.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

public class TimeUtil {
    private static SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

    public static String formatToFullDate(int year, int month, int date) {
        String fullDate = year + "-" + month + "-" + date;
        try {
            return fullDateFormat.format(Objects.requireNonNull(fullDateFormat.parse(fullDate)));
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String formatToFullDate(String date) {
        try {
            return fullDateFormat.format(Objects.requireNonNull(fullDateFormat.parse(date)));
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

}

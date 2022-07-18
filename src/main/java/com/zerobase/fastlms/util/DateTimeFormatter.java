package com.zerobase.fastlms.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTimeFormatter {
    private static final java.time.format.DateTimeFormatter BASIC_FORMAT = java.time.format.DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    public static String dateTimeToString(LocalDateTime localDateTime) {
        return localDateTime != null ? localDateTime.format(BASIC_FORMAT) : "";
    }

    public static String dateToString(LocalDate localDate) {
        return localDate != null ? localDate.format(java.time.format.DateTimeFormatter.ISO_DATE) : "";
    }

}

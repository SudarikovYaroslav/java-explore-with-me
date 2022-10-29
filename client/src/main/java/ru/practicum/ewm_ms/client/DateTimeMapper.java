package ru.practicum.ewm_ms.client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeMapper {

    private DateTimeMapper() {
    }

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String toString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
        return date.format(formatter);
    }
}
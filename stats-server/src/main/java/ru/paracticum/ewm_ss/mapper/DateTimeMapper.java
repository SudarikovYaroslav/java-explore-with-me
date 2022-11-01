package ru.paracticum.ewm_ss.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateTimeMapper {

    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    public static LocalDateTime toDateTime(String str) {
        return LocalDateTime.parse(str, formatter);
    }

    public static String toString(LocalDateTime date) {
        return date.format(formatter);
    }
}
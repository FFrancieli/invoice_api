package com.acme.parsers;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class TimestampParser {

    private static final String SYSTEM_TIMEZONE_ID = TimeZone.getDefault().getID();

    public static Timestamp fromString(String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date);

        return Timestamp.valueOf(localDateTime);
    }

    public static String toString(Timestamp timestamp, DateTimeFormatter dateTimeFormat) {
        ZonedDateTime dateTime = ZonedDateTime.of(timestamp.toLocalDateTime(), ZoneId.of(SYSTEM_TIMEZONE_ID));

        return dateTime.format(dateTimeFormat);
    }
}

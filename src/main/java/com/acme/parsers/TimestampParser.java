package com.acme.parsers;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TimestampParser {

    public static Timestamp fromString(String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date);

        return Timestamp.valueOf(localDateTime);
    }
}

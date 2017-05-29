package com.acme.parsers;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class TimestampParserTest {

    @Test
    public void parsesStringToTimestampFormat() throws Exception {
        Timestamp timestamp =  TimestampParser.fromString("2017-12-20T00:00:00");

        Timestamp expectedTimestamp = Timestamp.valueOf("2017-12-20 00:00:00");

        assertThat(timestamp, is(expectedTimestamp));
    }

    @Test
    public void parsesTimestampToStringFormat() throws Exception {
        Timestamp timestamp = new Timestamp(1415844000000L);

        String dateTime  = TimestampParser.toString(timestamp, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        assertThat(dateTime, is("2014-11-13T00:00:00"));
    }
}
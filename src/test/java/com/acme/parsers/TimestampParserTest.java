package com.acme.parsers;

import org.junit.Test;

import java.sql.Timestamp;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


public class TimestampParserTest {

    @Test
    public void parsesStringToTimestampFormat() throws Exception {
        Timestamp timestamp =  TimestampParser.fromString("2017-12-20T00:00:00");

        Timestamp expectedTimestamp = Timestamp.valueOf("2017-12-20 00:00:00");

        assertThat(timestamp, is(expectedTimestamp));
    }
}
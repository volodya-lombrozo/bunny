package org.lombrozo.bunny.message.properties;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class TimestampTest {


    @Test
    public void propertyTest_dateConstructor() {
        Date date = new Date();
        String expected = String.valueOf(date.toInstant().toEpochMilli());

        Property property = new Timestamp(date);

        assertEquals(expected, property.value());
        assertEquals(PropertyKey.TIMESTAMP, property.key());
    }


}
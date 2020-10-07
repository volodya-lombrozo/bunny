package org.lombrozo.bunny.message.properties;

import org.junit.Test;

import static org.junit.Assert.*;

public class MessageIdTest {


    @Test
    public void propertyTest() {
        String expected = "expected";

        Property property = new MessageId(expected);

        assertEquals(expected, property.value());
        assertEquals(PropertyKey.MESSAGE_ID, property.key());
    }

}
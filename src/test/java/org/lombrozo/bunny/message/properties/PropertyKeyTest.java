package org.lombrozo.bunny.message.properties;

import org.junit.Test;

import static org.junit.Assert.*;

public class PropertyKeyTest {

    @Test
    public void toStringTest() {
        String expected = "content_type";
        PropertyKey key = PropertyKey.CONTENT_TYPE;

        String stringKey = key.toString();

        assertEquals(expected, stringKey);
    }

}
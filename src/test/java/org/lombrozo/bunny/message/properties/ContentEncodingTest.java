package org.lombrozo.bunny.message.properties;

import org.junit.Test;

import static org.junit.Assert.*;

public class ContentEncodingTest {


    @Test
    public void propertyTest() {
        String expected = "expected";

        Property property = new ContentEncoding(expected);

        assertEquals(expected, property.value());
        assertEquals(PropertyKey.CONTENT_ENCODING, property.key());
    }

}
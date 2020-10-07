package org.lombrozo.bunny.message.properties;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExpirationTest {

    @Test
    public void propertyTest() {
        String expected = "expected";

        Property property = new Expiration(expected);

        assertEquals(expected, property.value());
        assertEquals(PropertyKey.EXPIRATION, property.key());
    }

}
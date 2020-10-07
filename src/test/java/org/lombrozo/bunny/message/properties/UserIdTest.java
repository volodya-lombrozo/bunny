package org.lombrozo.bunny.message.properties;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserIdTest {


    @Test
    public void propertyTest() {
        String expected = "expected";

        Property property = new UserId(expected);

        assertEquals(expected, property.value());
        assertEquals(PropertyKey.USER_ID, property.key());
        assertTrue(property.isNotEmpty());
    }

}
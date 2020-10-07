package org.lombrozo.bunny.message.properties;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnknownPropertyTest {


    @Test
    public void propertyTest() {
        String expected = "expected";

        Property property = new UnknownProperty(expected);

        assertEquals(expected, property.value());
        assertEquals(PropertyKey.UNKNOWN, property.key());
        assertFalse(property.isNotEmpty());
    }

}
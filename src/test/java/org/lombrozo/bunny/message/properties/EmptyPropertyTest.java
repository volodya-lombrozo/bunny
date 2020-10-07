package org.lombrozo.bunny.message.properties;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmptyPropertyTest {

    @Test
    public void propertyTest() {
        String expectedEmpty = "";

        Property.Empty empty = new Property.Empty();

        assertEquals(PropertyKey.UNKNOWN, empty.key());
        assertEquals(expectedEmpty, empty.value());
        assertFalse(empty.isNotEmpty());
    }

}
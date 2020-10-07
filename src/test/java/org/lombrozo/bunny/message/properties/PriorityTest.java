package org.lombrozo.bunny.message.properties;

import org.junit.Test;

import static org.junit.Assert.*;

public class PriorityTest {

    @Test
    public void propertyTest() {
        String expected = "expected";

        Property property = new Priority(expected);

        assertEquals(expected, property.value());
        assertEquals(PropertyKey.PRIORITY, property.key());
    }


    @Test
    public void propertyTest_intConstructor() {
        String expected = "1";

        Property property = new Priority(1);

        assertEquals(expected, property.value());
        assertEquals(PropertyKey.PRIORITY, property.key());
    }
}
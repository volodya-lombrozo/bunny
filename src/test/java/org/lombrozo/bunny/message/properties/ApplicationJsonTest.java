package org.lombrozo.bunny.message.properties;

import org.junit.Test;

import static org.junit.Assert.*;

public class ApplicationJsonTest {

    @Test
    public void propertyTest() {
        ApplicationJson property = new ApplicationJson();

        assertEquals(PropertyKey.CONTENT_TYPE, property.key());
        assertEquals("application/json", property.value());
    }


}
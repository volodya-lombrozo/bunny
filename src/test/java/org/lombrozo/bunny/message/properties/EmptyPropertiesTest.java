package org.lombrozo.bunny.message.properties;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmptyPropertiesTest {

    @Test
    public void getProperty() {
        String expectedEmpty = "";

        EmptyProperties properties = new EmptyProperties();

        String propValue = properties.property(PropertyKey.CONTENT_TYPE);
        assertEquals(expectedEmpty, propValue);
    }

    @Test
    public void putProperty_ignoreAdding() {
        String expectedEmpty = "";
        EmptyProperties properties = new EmptyProperties();
        ApplicationJson property = new ApplicationJson();

        properties.put(property);

        String propValue = properties.property(property.key());
        properties.containsProperty(property.key());
        assertEquals(expectedEmpty, propValue);
    }

    @Test
    public void addAllProperties_ignore() {
        EmptyProperties properties = new EmptyProperties();
        ApplicationJson first = new ApplicationJson();
        CorrelationId second = new CorrelationId();

        Properties afterAdding = properties.addAll(first, second);

        assertFalse(afterAdding.containsProperty(first.key()));
        assertFalse(afterAdding.containsProperty(second.key()));
    }
}
package org.lombrozo.bunny.message.properties;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.*;

public class PropertiesSetTest {

    @Test
    public void property() {
        Properties props = new PropertiesSet();
        String expectedEmpty = "";

        String actual = props.property(PropertyKey.CONTENT_TYPE);

        assertEquals(expectedEmpty, actual);
    }

    @Test
    public void put() {
        Properties props = new PropertiesSet();
        CorrelationId property = new CorrelationId();
        props.put(property);

        String actual = props.property(property.key());

        assertEquals(property.value(), actual);
        assertTrue(props.containsProperty(property.key()));
    }


    @Test
    public void addAll() {
        PropertiesSet properties = new PropertiesSet();
        ApplicationJson second = new ApplicationJson();
        CorrelationId first = new CorrelationId();

        Properties afterAdding = properties.addAll(first, second);

        assertTrue(afterAdding.containsProperty(first.key()));
        assertTrue(afterAdding.containsProperty(second.key()));
    }

    @Test
    public void toStringTest() {
        Map<PropertyKey, Property> properties = new HashMap<>();
        PropertiesSet props = new PropertiesSet(properties);
        String expected = "PropertiesSet{properties=" + properties + "}";

        String actual = props.toString();

        assertEquals(expected, actual);
    }

}
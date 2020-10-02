package org.lombrozo.bunny.message;

import org.junit.Test;
import org.lombrozo.bunny.message.properties.PropertyKey;
import org.lombrozo.bunny.message.properties.Type;

import static org.junit.Assert.*;

public class TypeTest {

    @Test
    public void simpleStringTest() {
        String expectedValue = "some.type";
        Type type = new Type(expectedValue);

        String actualValue = type.value();

        assertEquals(expectedValue, actualValue);
        assertEquals(PropertyKey.TYPE, type.key());
    }


    @Test
    public void classTypeTest() {
        String expectedValue = "java.lang.String";

        Type type = new Type(String.class);

        assertEquals(expectedValue, type.value());
        assertEquals(PropertyKey.TYPE, type.key());
    }

    @Test
    public void equalsTest() {
        Type first = new Type(String.class);
        Type second = new Type(String.class);

        assertEquals(first, second);
    }

    @Test
    public void hashTest() {
        Type first = new Type(String.class);
        Type second = new Type(String.class);

        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void toStringTest() {
        Type type = new Type(String.class);
        String expected = "type=" + String.class.getName();

        String actual = type.toString();

        assertEquals(expected, actual);
    }
}
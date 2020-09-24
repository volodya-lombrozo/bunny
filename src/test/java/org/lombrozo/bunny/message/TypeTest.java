package org.lombrozo.bunny.message;

import org.junit.Test;

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

}
package org.lombrozo.bunny.message.header;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpringTypeIdTest {

    private final String expectedKey = "__TypeId__";

    @Test
    public void headerTest_stringConstructor() {
        String expectedValue = "value";

        SpringTypeId header = new SpringTypeId(expectedValue);

        assertEquals(expectedKey, header.key());
        assertEquals(expectedValue, header.value());
    }

    @Test
    public void headerTest_typeConstructor() {
        Class<String> type = String.class;

        SpringTypeId header = new SpringTypeId(type);

        assertEquals(expectedKey, header.key());
        assertEquals("java.lang.String", header.value());
    }

}
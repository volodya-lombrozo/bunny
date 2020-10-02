package org.lombrozo.bunny.message.header;

import org.junit.Test;

import static org.junit.Assert.*;

public class FromTest {

    @Test
    public void headerTest() {
        String expected = "Value";

        From from = new From(expected);

        assertEquals("from", from.key());
        assertEquals(expected, from.value());
    }

}
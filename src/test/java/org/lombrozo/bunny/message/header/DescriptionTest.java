package org.lombrozo.bunny.message.header;

import org.junit.Test;

import static org.junit.Assert.*;

public class DescriptionTest {

    @Test
    public void headerTest() {
        String expected = "Some description";

        Description description = new Description(expected);

        assertEquals("description", description.key());
        assertEquals(expected, description.value());
    }


}
package org.lombrozo.bunny.message.delivery;

import org.junit.Test;

import static org.junit.Assert.*;

public class NonPersistentTest {

    @Test
    public void toStringTest() {
        String expected = "NonPersistent{}";

        NonPersistent persistent = new NonPersistent();

        String actual = persistent.toString();
        assertEquals(expected, actual);
    }

}
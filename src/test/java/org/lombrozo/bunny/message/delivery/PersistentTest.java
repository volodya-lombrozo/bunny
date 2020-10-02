package org.lombrozo.bunny.message.delivery;

import org.junit.Test;

import static org.junit.Assert.*;

public class PersistentTest {

    @Test
    public void toStringTest() {
        String expected = "Persistent{}";

        Persistent persistent = new Persistent();

        assertEquals(expected, persistent.toString());
    }

}
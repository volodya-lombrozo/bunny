package org.lombrozo.bunny.message.header;

import org.junit.Test;

import static org.junit.Assert.*;

public class HeaderTest {

    @Test
    public void fakeHeaderTest() {
        String expectedEmpty = "";

        Header.Fake fake = new Header.Fake();

        assertEquals(expectedEmpty, fake.key());
        assertEquals(expectedEmpty, fake.value());
    }

}
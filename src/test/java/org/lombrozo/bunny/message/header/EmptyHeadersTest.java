package org.lombrozo.bunny.message.header;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmptyHeadersTest {

    @Test
    public void ignoreAnyAddingTest() {
        EmptyHeaders headers = new EmptyHeaders();

        Headers actual = headers.add(new Header.Fake()).add(new Headers.Fake());

        assertTrue(actual.toMap().isEmpty());
    }

}
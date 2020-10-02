package org.lombrozo.bunny.message.header;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class HeadersTest {

    @Test
    public void fakeHeadersTest() {
        Headers.Fake fake = new Headers.Fake();

        Headers same = fake.add(new Headers.Fake()).add(new Header.Fake());

        Optional<Header> header = fake.header("");
        assertTrue(header.isPresent());
        assertEquals(fake, same);
        assertTrue(same.toMap().isEmpty());
    }

}
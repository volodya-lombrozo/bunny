package org.lombrozo.bunny.message.header;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class HeadersMapTest {

    @Test
    public void addSingleHeaderTest() {
        Headers headers = new HeadersMap();
        Header.Fake addedHeader = new Header.Fake();

        headers.add(addedHeader);

        Optional<Header> optionalHeader = headers.header(addedHeader.key());
        assertTrue(optionalHeader.isPresent());
        assertEquals(addedHeader.key(), optionalHeader.get().key());
        assertEquals(addedHeader.value(), optionalHeader.get().value());
    }


    @Test
    public void addManyHeaderTest() {
        From fromHeader = new From("f");
        Description descriptionHeader = new Description("d");
        Headers first = new HeadersMap(descriptionHeader);
        Headers second = new HeadersMap(fromHeader);

        first.add(second);

        assertEquals(2, first.toMap().size());
        assertTrue(first.header(fromHeader.key()).isPresent());
        assertTrue(first.header(descriptionHeader.key()).isPresent());
    }

    @Test
    public void headerNotFoundTest() {
        Headers headers = new HeadersMap();

        Optional<Header> optionalHeader = headers.header("notExistsKey");

        assertFalse(optionalHeader.isPresent());
    }
}
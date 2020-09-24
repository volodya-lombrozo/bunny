package org.lombrozo.bunny.message;

import org.junit.Test;

import static org.junit.Assert.*;

public class ByteBodyTest {

    @Test
    public void toByteArray_emptyConstructor_successful() {
        Body body = new ByteBody();

        byte[] bytes = body.toByteArray();

        assertNotNull(bytes);
        assertEquals(0, bytes.length);
    }

    @Test
    public void toByteArray_byteArrayConstructor_successful() {
        byte[] expected = new byte[10];
        Body body = new ByteBody(expected);

        byte[] actual = body.toByteArray();

        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }
}
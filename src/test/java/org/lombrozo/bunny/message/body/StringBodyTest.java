package org.lombrozo.bunny.message.body;

import org.junit.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.*;

public class StringBodyTest {

    @Test
    public void toByteArrayTest() {
        String expected = "Some expected";
        StringBody stringBody = new StringBody(expected);

        byte[] bytes = stringBody.toByteArray();

        String actual = new String(bytes);
        assertEquals(expected, actual);
    }


    @Test
    public void toByteArrayTest_emptyConstructor() {
        StringBody stringBody = new StringBody();

        byte[] bytes = stringBody.toByteArray();

        assertNotNull(bytes);
    }


}
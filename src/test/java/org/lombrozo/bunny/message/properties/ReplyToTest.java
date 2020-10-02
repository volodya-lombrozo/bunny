package org.lombrozo.bunny.message.properties;

import org.junit.Test;

import static org.junit.Assert.*;

public class ReplyToTest {

    @Test
    public void replyTo_emptyConstructor() {
        ReplyTo replyTo = new ReplyTo();

        assertEquals(PropertyKey.REPLY_TO, replyTo.key());
        assertTrue(replyTo.value().isEmpty());
    }

    @Test
    public void toStringTest() {
        String dest = "dest";
        ReplyTo replyTo = new ReplyTo(dest);
        String expected = "ReplyTo{value='" + dest + "'}";

        String actual = replyTo.toString();

        assertEquals(expected, actual);
    }

}
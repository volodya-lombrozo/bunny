package org.lombrozo.bunny.message.routing;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringRoutingKeyTest {


    @Test
    public void asStringTest() {
        String expected = "value";
        RoutingKey routingKey = new StringRoutingKey(expected);

        String actual = routingKey.asString();

        assertEquals(expected, actual);
    }

}
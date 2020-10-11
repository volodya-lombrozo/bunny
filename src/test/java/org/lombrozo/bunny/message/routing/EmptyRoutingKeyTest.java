package org.lombrozo.bunny.message.routing;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmptyRoutingKeyTest {

    @Test
    public void asString() {
        RoutingKey empty = new EmptyRoutingKey();

        String actual = empty.asString();

        assertTrue(actual.isEmpty());
    }
}
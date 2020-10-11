package org.lombrozo.bunny.message.routing;

import org.junit.Test;

import static org.junit.Assert.*;

public class FakeRoutingKeyTest {

    @Test
    public void asStringTest() {
        RoutingKey routingKey = new RoutingKey.Fake();

        String actual = routingKey.asString();

        assertFalse(actual.isEmpty());
    }
}
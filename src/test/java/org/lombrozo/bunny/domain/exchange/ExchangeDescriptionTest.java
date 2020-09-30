package org.lombrozo.bunny.domain.exchange;

import org.junit.Test;
import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.domain.queue.QueueDescription;

import static org.junit.Assert.*;

public class ExchangeDescriptionTest {


    @Test
    public void durableAndInternal() {
        TopicExchange exchange = new TopicExchange(new Connection.Fake(), "", new Durable(new Internal()));

        ExchangeDescription desc = exchange.description();

        assertTrue(desc.durable());
        assertTrue(desc.internal());
        assertFalse(desc.autoDelete());
    }


    @Test
    public void autoDeleteOnly() {
        TopicExchange exchange = new TopicExchange(new Connection.Fake(), "", new AutoDelete());

        ExchangeDescription desc = exchange.description();

        assertFalse(desc.durable());
        assertFalse(desc.internal());
        assertTrue(desc.autoDelete());
    }

    @Test
    public void durableOnly() {
        TopicExchange exchange = new TopicExchange(new Connection.Fake(), "", new Durable());

        ExchangeDescription desc = exchange.description();

        assertTrue(desc.durable());
        assertFalse(desc.internal());
        assertFalse(desc.autoDelete());
    }

    @Test
    public void internalOnly() {
        TopicExchange exchange = new TopicExchange(new Connection.Fake(), "", new Internal());

        ExchangeDescription desc = exchange.description();

        assertFalse(desc.durable());
        assertTrue(desc.internal());
        assertFalse(desc.autoDelete());
    }
}
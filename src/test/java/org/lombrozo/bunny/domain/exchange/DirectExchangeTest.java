package org.lombrozo.bunny.domain.exchange;

import org.junit.Test;
import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DirectExchangeTest {


    @Test
    public void allFieldsTest() {
        String expectedName = "name";
        String expectedKey = "key";
        Exchange exchange = new DirectExchange(expectedName, expectedKey, new Connection.Fake());

        ExchangeType type = exchange.type();

        assertEquals(ExchangeType.DIRECT, type);
        assertEquals(expectedName, exchange.name());
        assertEquals(expectedName, exchange.exchangeName());
        assertEquals(expectedKey, exchange.routingKey());
    }

    @Test
    public void withoutRoutingKeyTest() {
        Exchange exchange = new DirectExchange("", new Connection.Fake());

        String routingKey = exchange.routingKey();

        assertEquals("", routingKey);
    }


    @Test
    public void createTest() throws RabbitException {
        Exchange mock = Mockito.mock(Exchange.class);
        Exchange exchange = new DirectExchange(mock);

        exchange.declare();

        verify(mock, times(1)).declare();
    }


    @Test
    public void sendMessageTest() throws RabbitException {
        Exchange mock = Mockito.mock(Exchange.class);
        Exchange exchange = new DirectExchange(mock);
        Message.Fake message = new Message.Fake();

        exchange.send(message);

        verify(mock, times(1)).send(message);
    }

}
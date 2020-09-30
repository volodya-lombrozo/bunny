package org.lombrozo.bunny.domain.exchange;

import org.junit.Test;
import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class TopicExchangeTest {

    @Test
    public void allFieldsTest() {
        String expectedName = "name";

        Exchange exchange = new TopicExchange(new Connection.Fake(), expectedName);
        ExchangeType type = exchange.type();

        assertEquals(ExchangeType.TOPIC, type);
        assertEquals(expectedName, exchange.name());
    }

    @Test
    public void createTest() throws RabbitException {
        Exchange mock = Mockito.mock(Exchange.class);
        Exchange exchange = new TopicExchange(mock);

        exchange.declare();

        verify(mock, times(1)).declare();
    }


    @Test
    public void sendMessageTest() throws RabbitException {
        Exchange mock = Mockito.mock(Exchange.class);
        Exchange exchange = new TopicExchange(mock);
        Message.Fake message = new Message.Fake();

        exchange.send(message);

        verify(mock, times(1)).send(message);
    }
}
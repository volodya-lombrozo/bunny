package org.lombrozo.bunny.domain.exchange;

import org.junit.Before;
import org.junit.Test;
import org.lombrozo.bunny.connection.Channel;
import org.lombrozo.bunny.connection.TestConnection;
import org.lombrozo.bunny.domain.destination.ExchangeDestination;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


public class NamedExchangeTest {

    TestConnection connection;
    Channel channel;

    @Before
    public void setUp() {
        connection = new TestConnection(Mockito.mock(Channel.class));
        channel = connection.channel();
    }

    @Test
    public void createTest() throws RabbitException {
        NamedExchange exchange = new NamedExchange(connection, "name", ExchangeType.DIRECT);

        exchange.declare();

        verify(channel, times(1)).declare(exchange);
    }


    @Test
    public void sendTest() throws RabbitException {
        NamedExchange exchange = new NamedExchange(connection, "name", ExchangeType.DIRECT);
        Message.Fake message = new Message.Fake();

        exchange.send(message);

        verify(channel, times(1)).publish(new ExchangeDestination(exchange, message.routingKey()), message);
    }
}
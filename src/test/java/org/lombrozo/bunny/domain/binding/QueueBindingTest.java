package org.lombrozo.bunny.domain.binding;

import org.junit.Test;
import org.lombrozo.bunny.connection.Channel;
import org.lombrozo.bunny.connection.TestConnection;
import org.lombrozo.bunny.domain.exchange.Exchange;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class QueueBindingTest {

    @Test
    public void createTest() throws RabbitException {
        Exchange.Fake exchange = new Exchange.Fake();
        Queue.Fake queue = new Queue.Fake();
        String routingKey = "routingKey";
        Channel mockChannel = Mockito.mock(Channel.class);
        QueueBinding binding = new QueueBinding(exchange, queue, routingKey, new TestConnection(mockChannel));

        binding.declare();

        assertEquals(exchange.name(), binding.source());
        assertEquals(queue.name(), binding.destination());
        assertEquals(routingKey, binding.routingKey());
        verify(mockChannel, times(1)).declare(binding);
    }


}

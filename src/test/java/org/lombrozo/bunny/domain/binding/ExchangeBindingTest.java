package org.lombrozo.bunny.domain.binding;

import org.junit.Test;
import org.lombrozo.bunny.connection.Channel;
import org.lombrozo.bunny.connection.TestConnection;
import org.lombrozo.bunny.domain.exchange.Exchange;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ExchangeBindingTest {

    @Test
    public void createTest() throws RabbitException {
        Exchange.Fake source = new Exchange.Fake();
        Exchange.Fake destination = new Exchange.Fake();
        String routingKey = "routingKey";
        Channel mockChannel = Mockito.mock(Channel.class);
        ExchangeBinding binding = new ExchangeBinding(source, destination, routingKey, new TestConnection(mockChannel));

        binding.declare();

        assertEquals(source.name(), binding.source());
        assertEquals(destination.name(), binding.destination());
        assertEquals(routingKey, binding.routingKey());
        verify(mockChannel, times(1)).create(binding);
    }

}
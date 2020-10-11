package org.lombrozo.bunny.domain.destination;

import org.junit.Test;
import org.lombrozo.bunny.domain.exchange.Exchange;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ExchangeDestinationTest {

    @Test
    public void fieldsTest() {
        String routingKey = "routingKey";
        Exchange.Fake exchange = new Exchange.Fake();

        Destination destination = new ExchangeDestination(exchange, routingKey);

        assertEquals(exchange.name(), destination.exchangeName());
        assertEquals(routingKey, destination.routingKey());
    }

    @Test
    public void hashCodeTest() {
        Exchange.Fake exchange = new Exchange.Fake();
        Destination first = new ExchangeDestination(exchange);
        Destination second = new ExchangeDestination(exchange);

        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    public void toStringTest() {
        Exchange.Fake exchange = new Exchange.Fake();
        Destination destination = new ExchangeDestination(exchange);
        String expected = "ExchangeDestination(exchange='" + exchange + "', routingKey='" + destination.routingKey() + "')";

        String actual = destination.toString();

        System.out.println(actual);
        assertEquals(expected, actual);
    }

}
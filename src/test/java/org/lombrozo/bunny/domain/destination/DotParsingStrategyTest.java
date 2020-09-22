package org.lombrozo.bunny.domain.destination;

import org.junit.Test;

import static org.junit.Assert.*;

public class DotParsingStrategyTest {

    @Test
    public void exchange() {
        String exchangeName = "exchangeName";
        String routingKey = "routingKey";
        String rawString = exchangeName + "." + routingKey;

        String exchange = new DotParsingStrategy().exchange(rawString);

        assertEquals(exchangeName, exchange);
    }

    @Test
    public void routingKey() {
        String expectedExchangeName = "exchangeName";
        String expectedRoutingKey = "routingKey";
        String rawString = expectedExchangeName + "." + expectedRoutingKey;

        String routingKey = new DotParsingStrategy().routingKey(rawString);

        assertEquals(expectedRoutingKey, routingKey);
    }

    @Test
    public void exchange_wrongRawFormat() {
        String routingKey = "routingKey";
        String rawString = "." + routingKey;

        String exchange = new DotParsingStrategy().exchange(rawString);

        assertEquals("", exchange);
    }


    @Test
    public void exchange_emptyRawString() {
        String rawString = "";

        String exchange = new DotParsingStrategy().exchange(rawString);

        assertEquals("", exchange);
    }


    @Test
    public void routingKey_wrongRawFormat() {
        String rawString = ".";

        String routingKey = new DotParsingStrategy().routingKey(rawString);

        assertEquals("", routingKey);
    }


    @Test
    public void routingKey_emptyString() {
        String rawString = "";

        String routingKey = new DotParsingStrategy().routingKey(rawString);

        assertEquals("", routingKey);
    }

}
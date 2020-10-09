package org.lombrozo.bunny.domain.destination;

import org.junit.Test;
import org.lombrozo.bunny.domain.destination.reply.DotReplyInfo;
import org.lombrozo.bunny.domain.destination.reply.ReplyDestination;

import static org.junit.Assert.*;

public class DotReplyInfoTest {

    @Test
    public void exchange() {
        String exchangeName = "exchangeName";
        String routingKey = "routingKey";
        String rawString = exchangeName + "." + routingKey;

        String exchange = new DotReplyInfo(rawString).exchange();

        assertEquals(exchangeName, exchange);
    }

    @Test
    public void routingKey() {
        String expectedExchangeName = "exchangeName";
        String expectedRoutingKey = "routingKey";
        String rawString = expectedExchangeName + "." + expectedRoutingKey;

        String routingKey = new DotReplyInfo(rawString).routingKey();

        assertEquals(expectedRoutingKey, routingKey);
    }

    @Test
    public void exchange_wrongRawFormat() {
        String routingKey = "routingKey";
        String rawString = "." + routingKey;

        String exchange = new DotReplyInfo(rawString).exchange();

        assertEquals("", exchange);
    }


    @Test
    public void exchange_emptyRawString() {
        String rawString = "";

        String exchange = new DotReplyInfo(rawString).exchange();

        assertEquals("", exchange);
    }


    @Test
    public void routingKey_wrongRawFormat() {
        String rawString = ".";

        String routingKey = new DotReplyInfo(rawString).routingKey();

        assertEquals("", routingKey);
    }


    @Test
    public void routingKey_emptyString() {
        String rawString = "";

        String routingKey = new DotReplyInfo(rawString).routingKey();

        assertEquals("", routingKey);
    }

}
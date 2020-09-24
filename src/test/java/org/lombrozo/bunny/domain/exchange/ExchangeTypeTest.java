package org.lombrozo.bunny.domain.exchange;

import org.junit.Test;

import static org.junit.Assert.*;

public class ExchangeTypeTest {

    @Test
    public void toStringTest(){
        ExchangeType fanout = ExchangeType.FANOUT;
        ExchangeType direct = ExchangeType.DIRECT;
        ExchangeType topic = ExchangeType.TOPIC;


        assertEquals("fanout", fanout.toString());
        assertEquals("direct", direct.toString());
        assertEquals("topic", topic.toString());
    }

}
package org.lombrozo.bunny.connection;

import org.junit.Test;
import org.lombrozo.bunny.domain.exchange.Exchange;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.function.LatchWork;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;


import java.util.HashMap;
import java.util.concurrent.BlockingDeque;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class TestChannelTest {


    @Test
    public void publishAndListenTest_successful() throws RabbitException {
        Channel channel = new TestChannel();
        Queue queue = new Queue.Fake();
        LatchWork work = new LatchWork();
        channel.publish(queue, new Message.Fake());

        channel.listenQueue(queue, work);

        work.await();
        assertNotNull(work.lastMessage());
    }

    @Test
    public void createQueue() {
        HashMap<String, BlockingDeque<Message>> map = new HashMap<>();
        TestChannel channel = new TestChannel(map);

        channel.create(new Queue.Fake());

        assertEquals(1, map.size());
    }


    @Test
    public void createExchange() {
        HashMap<String, BlockingDeque<Message>> map = new HashMap<>();
        TestChannel channel = new TestChannel(map);

        channel.create(new Exchange.Fake());

        assertEquals(1, map.size());
    }

}
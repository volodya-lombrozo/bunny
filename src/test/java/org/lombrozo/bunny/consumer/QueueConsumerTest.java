package org.lombrozo.bunny.consumer;

import org.junit.Test;
import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.connection.TestConnection;
import org.lombrozo.bunny.domain.queue.NamedQueue;
import org.lombrozo.bunny.function.ConsumerHandler;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.RandomString;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class QueueConsumerTest {


    @Test(timeout = 50)
    public void consumer_successful_queueToConstructor() throws RabbitException, InterruptedException {
        String queueName = new RandomString().toString();
        CountDownLatch latch = new CountDownLatch(1);
        Connection connection = new TestConnection();
        connection.allocateChannels();
        NamedQueue namedQueue = new NamedQueue(connection, queueName);
        ResponsibleQueueConsumer consumer = new ResponsibleQueueConsumer(namedQueue, connection);

        consumer.subscribe(new ConsumerHandler(message -> latch.countDown()));

        namedQueue.send(new Message.Fake());
        latch.await();
        assertEquals(0L, latch.getCount());
    }


    @Test(timeout = 50)
    public void consumer_successful_queueNameToConstructor() throws RabbitException, InterruptedException {
        String queueName = new RandomString().toString();
        CountDownLatch latch = new CountDownLatch(1);
        Connection connection = new TestConnection();
        connection.allocateChannels();
        ResponsibleQueueConsumer consumer = new ResponsibleQueueConsumer(queueName, connection);

        consumer.subscribe(new ConsumerHandler(message -> latch.countDown()));

        new NamedQueue(connection, queueName).send(new Message.Fake());
        latch.await();
        assertEquals(0L, latch.getCount());
    }
}
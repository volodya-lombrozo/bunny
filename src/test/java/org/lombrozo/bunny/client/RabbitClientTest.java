package org.lombrozo.bunny.client;

import org.junit.Before;
import org.junit.Test;
import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.connection.TestConnection;
import org.lombrozo.bunny.consumer.QueueConsumer;
import org.lombrozo.bunny.domain.queue.NamedQueue;
import org.lombrozo.bunny.function.Handler;
import org.lombrozo.bunny.message.*;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class RabbitClientTest {

    Connection connection;
    String sendQueueName = "send";
    String replyQueueName = "reply";


    @Before
    public void setUp() throws RabbitException {
        connection = new TestConnection();
        connection.allocateChannels();
    }

    @Test(timeout = 100)
    public void send_queueNamesOnly_successfulSendAndReceive() throws RabbitException {
        RabbitClient client = new RabbitClient(connection, sendQueueName, replyQueueName);
        Message message = new RabbitMessage("hello", new ReplyTo("." + replyQueueName));
        new QueueConsumer(sendQueueName, connection).subscribe(new Handler.Echo());

        FutureMessage futureMessage = client.send(message);

        Message response = futureMessage.block();
        assertNotNull(response);
    }


    @Test(timeout = 100)
    public void send_queuesConstructor_successfulSendAndReceive() throws RabbitException {
        NamedQueue sendQueue = new NamedQueue(sendQueueName, connection);
        NamedQueue replyQueue = new NamedQueue(replyQueueName, connection);
        RabbitClient client = new RabbitClient(sendQueue, replyQueue);
        Message message = new RabbitMessage("hello", new ReplyToDestination(replyQueue));
        new QueueConsumer(sendQueueName, connection).subscribe(new Handler.Echo());

        FutureMessage futureMessage = client.send(message);

        Message response = futureMessage.block();
        assertNotNull(response);
    }

    @Test(timeout = 100)
    public void publish() throws RabbitException, InterruptedException {
        NamedQueue sendQueue = new NamedQueue(sendQueueName, connection);
        NamedQueue replyQueue = new NamedQueue(replyQueueName, connection);
        RabbitClient client = new RabbitClient(sendQueue, replyQueue);
        CountDownLatch latch = new CountDownLatch(1);
        connection.channel().listenQueue(sendQueue, m -> latch.countDown());

        client.publish(new Message.Fake());

        latch.await();
        assertEquals(0, latch.getCount());
    }

}
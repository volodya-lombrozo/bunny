package org.lombrozo.bunny.client;

import org.junit.Before;
import org.junit.Test;
import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.connection.TestConnection;
import org.lombrozo.bunny.consumer.ResponsibleQueueConsumer;
import org.lombrozo.bunny.domain.destination.QueueDestination;
import org.lombrozo.bunny.domain.queue.NamedQueue;
import org.lombrozo.bunny.function.Handler;
import org.lombrozo.bunny.message.*;
import org.lombrozo.bunny.message.properties.ReplyTo;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import static org.junit.Assert.*;

public class RabbitClientTest {

    private Connection connection;
    private final String sendQueueName = "send";
    private final String replyQueueName = "reply";

    @Before
    public void setUp() throws RabbitException {
        connection = new TestConnection();
        connection.allocateChannels();
    }

    @Test(timeout = 100)
    public void send_queueNamesOnly_successfulSendAndReceive() throws RabbitException {
        RabbitClient client = new RabbitClient(connection, replyQueueName);
        Message message = new RPCMessage("hello", new ReplyTo("." + replyQueueName));
        NamedQueue queue = new NamedQueue(connection, sendQueueName);
        new ResponsibleQueueConsumer(sendQueueName, connection)
                .subscribe(new Handler.Echo());

        MessagePipeline futureMessage = client.pipeline(queue, message).send();

        Message response = futureMessage.block();
        assertNotNull(response);
    }


    @Test(timeout = 100_000)
    public void send_queuesConstructor_successfulSendAndReceive() throws RabbitException {
        NamedQueue sendQueue = new NamedQueue(connection, sendQueueName);
        NamedQueue replyQueue = new NamedQueue(connection, replyQueueName);
        RabbitClient client = new RabbitClient(replyQueue);
        new ResponsibleQueueConsumer(sendQueueName, connection).subscribe(new Handler.Echo());
        Message message = new RPCMessage("hello", new ReplyTo(replyQueue));

        MessagePipeline futureMessage = client.pipeline(sendQueue, message)
                .addResponseConsumer(System.out::println)
                .send();

        Message response = futureMessage.block();
        assertNotNull(response);
    }

}
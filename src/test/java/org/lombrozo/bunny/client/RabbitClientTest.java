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
import org.lombrozo.bunny.message.properties.ReplyToDestination;
import org.lombrozo.bunny.util.exceptions.RabbitException;

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
        RabbitClient client = new RabbitClient(connection, replyQueueName);
        Message message = new RPCMessage("hello", new ReplyTo("." + replyQueueName));
        QueueDestination destination = new QueueDestination(new NamedQueue(sendQueueName, connection));

        new ResponsibleQueueConsumer(sendQueueName, connection)
                .subscribe(new Handler.Echo());

        FutureMessage futureMessage = client.send(destination, message);

        Message response = futureMessage.block();
        assertNotNull(response);
    }


    @Test(timeout = 100)
    public void send_queuesConstructor_successfulSendAndReceive() throws RabbitException {
        NamedQueue sendQueue = new NamedQueue(sendQueueName, connection);
        NamedQueue replyQueue = new NamedQueue(replyQueueName, connection);
        RabbitClient client = new RabbitClient(replyQueue);
        Message message = new RPCMessage("hello", new ReplyToDestination(replyQueue));
        new ResponsibleQueueConsumer(sendQueueName, connection).subscribe(new Handler.Echo());

        FutureMessage futureMessage = client.send(new QueueDestination(sendQueue), message);

        Message response = futureMessage.block();
        assertNotNull(response);
    }

}
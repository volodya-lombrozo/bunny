package org.lombrozo.bunny.consumer;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.domain.destination.reply.ReplyDestination;
import org.lombrozo.bunny.domain.queue.NamedQueue;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.function.Handler;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.message.properties.PropertyKey;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class ResponsibleQueueConsumer implements ResponsibleConsumer {

    private final Queue queue;
    private final Connection connection;

    public ResponsibleQueueConsumer(String queueName, Connection connection) {
        this(new NamedQueue(connection, queueName), connection);
    }

    public ResponsibleQueueConsumer(Queue queue, Connection connection) {
        this.queue = queue;
        this.connection = connection;
    }


    @Override
    public void subscribe(Handler handler) throws RabbitException {
        queue.subscribe(incoming -> {
            Message response = handler.handle(incoming);
            sendResponse(incoming, response);
        });
    }

    private void sendResponse(Message incoming, Message response) throws RabbitException {
        String replyTo = incoming.properties().property(PropertyKey.REPLY_TO);
        Destination destination = new ReplyDestination(replyTo);
        connection.channel().publish(destination, response);
    }
}

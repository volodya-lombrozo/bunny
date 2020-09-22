package org.lombrozo.bunny.consumer;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.domain.queue.NamedQueue;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.function.Handler;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class QueueConsumer implements ResponsibleConsumer {

    private final Queue queue;

    public QueueConsumer(String queueName, Connection connection) {
        this(new NamedQueue(queueName, connection));
    }

    public QueueConsumer(Queue queue) {
        this.queue = queue;
    }


    @Override
    public void subscribe(Handler handler) throws RabbitException {
        queue.subscribe(incoming -> {
            Message response = handler.handle(incoming);
            sendResponse(incoming, response);
        });
    }

    private void sendResponse(Message incoming, Message response) {


    }
}

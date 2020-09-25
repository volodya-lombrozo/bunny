package org.lombrozo.bunny.domain.destination;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class QueueDestination implements Destination {

    private final Queue queue;
    private final Connection connection;

    public QueueDestination(Queue queue, Connection connection) {
        this.queue = queue;
        this.connection = connection;
    }

    @Override
    public String exchangeName() {
        return "";
    }

    @Override
    public String routingKey() {
        return queue.name();
    }

    @Override
    public void send(Message message) throws RabbitException {
        connection.channel().publish(this, message);
    }
}

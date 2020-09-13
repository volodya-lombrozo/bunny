package org.lombrozo.bunny.destination;

import org.lombrozo.bunny.connections.Connection;
import org.lombrozo.bunny.domain.Exchange;
import org.lombrozo.bunny.domain.NamedQueue;
import org.lombrozo.bunny.domain.Queue;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class RabbitDestination implements Destination {

    private String name;
    private String routingKey;
    private Connection connection;

    public RabbitDestination(Connection connection, String queueName) {
        this(connection, new NamedQueue(queueName));
    }

    public RabbitDestination(Connection connection, Queue queue) {
        this(connection, "", queue.name());
    }

    public RabbitDestination(Connection connection, Exchange exchange, String routingKey) {
        this(connection, exchange.name(), routingKey);
    }

    public RabbitDestination(Connection connection, String name, String routingKey) {
        this.connection = connection;
        this.name = name;
        this.routingKey = routingKey;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String routingKey() {
        return routingKey;
    }

    @Override
    public void send(Message message) throws RabbitException {
        connection.channel().publish(this, message);
    }
}

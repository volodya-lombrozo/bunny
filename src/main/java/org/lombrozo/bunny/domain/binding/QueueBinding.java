package org.lombrozo.bunny.domain.binding;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.domain.exchange.Exchange;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class QueueBinding implements Binding {

    private final Exchange from;
    private final Queue to;
    private final String routingKey;
    private final Connection connection;

    public QueueBinding(Exchange from, Queue to, String routingKey, Connection connection) {
        this.from = from;
        this.to = to;
        this.routingKey = routingKey;
        this.connection = connection;
    }

    @Override
    public String source() {
        return from.name();
    }

    @Override
    public String destination() {
        return to.name();
    }

    @Override
    public String routingKey() {
        return routingKey;
    }

    @Override
    public void create() throws RabbitException {
        connection.channel().create(this);
    }
}

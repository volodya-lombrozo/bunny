package org.lombrozo.bunny.domain.queue;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.util.RandomString;
import org.lombrozo.bunny.function.Work;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.util.subscription.LatchSubscription;
import org.lombrozo.bunny.util.subscription.Subscription;

public class NamedQueue implements Queue {

    private final String queueName;
    private final Connection connection;
    private final QueueDescription description;

    public NamedQueue(Connection connection) {
        this(new RandomString().toString(), connection);
    }

    public NamedQueue(String queueName, Connection connection) {
        this(queueName, connection, new QueueDescription.Default());
    }

    public NamedQueue(Connection connection, QueueDescription description) {
        this(new RandomString().toString(), connection, description);
    }

    public NamedQueue(String queueName, Connection connection, QueueDescription description) {
        this.queueName = queueName;
        this.connection = connection;
        this.description = description;
    }

    @Override
    public String name() {
        return queueName;
    }

    @Override
    public void declare() throws RabbitException {
        connection.channel().create(this);
    }

    @Override
    public Subscription subscribe(Work work) throws RabbitException {
        connection.channel().listenQueue(this, work);
        return new LatchSubscription();
    }

    @Override
    public String exchangeName() {
        return "";
    }

    @Override
    public String routingKey() {
        return name();
    }

    @Override
    public void send(Message message) throws RabbitException {
        connection.channel().publish(this, message);
    }

    @Override
    public QueueDescription description() {
        return description;
    }
}

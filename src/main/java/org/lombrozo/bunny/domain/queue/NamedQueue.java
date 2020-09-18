package org.lombrozo.bunny.domain.queue;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.consumer.work.Work;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.util.subscription.LatchSubscription;
import org.lombrozo.bunny.util.subscription.Subscription;

public class NamedQueue implements Queue {

    private final String queueName;
    private final Connection connection;

    public NamedQueue(String queueName, Connection connection) {
        this.queueName = queueName;
        this.connection = connection;
    }

    @Override
    public String name() {
        return queueName;
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
}

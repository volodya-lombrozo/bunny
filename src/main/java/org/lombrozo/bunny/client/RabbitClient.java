package org.lombrozo.bunny.client;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.consumer.TargetConsumer;
import org.lombrozo.bunny.consumer.targets.QueueTarget;
import org.lombrozo.bunny.destination.Destination;
import org.lombrozo.bunny.destination.RabbitDestination;
import org.lombrozo.bunny.domain.Queue;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.util.subscription.ExpectingMessage;
import org.lombrozo.bunny.util.subscription.RabbitExpectingMessage;


public class RabbitClient implements Client {

    private final Destination destination;
    private final Connection connection;
    private final Queue listenQueue;


    public RabbitClient(Connection connection, Queue publishQueue, Queue listenQueue) {
        this(new RabbitDestination(connection, publishQueue), connection, listenQueue);
    }

    public RabbitClient(Destination destination, Connection connection, Queue listenQueue) {
        this.destination = destination;
        this.connection = connection;
        this.listenQueue = listenQueue;
    }

    @Override
    public ExpectingMessage send(Message message) throws RabbitException {
        ExpectingMessage observable = new RabbitExpectingMessage();
        destination.send(message);
        TargetConsumer consumer = new TargetConsumer(connection, new QueueTarget(listenQueue.name(), observable::publish));
        consumer.startListening();
        return observable;
    }

    @Override
    public void publish(Message message) throws RabbitException {
        destination.send(message);
    }

}

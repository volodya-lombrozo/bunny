package org.lombrozo.bunny.client;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.domain.destination.QueueDestination;
import org.lombrozo.bunny.domain.queue.NamedQueue;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.message.*;
import org.lombrozo.bunny.util.exceptions.EmptyCorrelationId;
import org.lombrozo.bunny.util.exceptions.RabbitException;


public class RabbitClient implements Client {

    private final Destination destination;
    private final Queue listenQueue;
    private final ResponseSource callbackSource;

    public RabbitClient(Connection connection, String destinationQueue, String replyQueue) {
        this(new QueueDestination(new NamedQueue(destinationQueue, connection)), new NamedQueue(replyQueue, connection),
                new MapResponseSource());
    }

    public RabbitClient(Queue destinationQueue, Queue queue) {
        this(new QueueDestination(destinationQueue), queue);
    }

    public RabbitClient(Destination destination, Queue replyQueue) {
        this(destination, replyQueue, new MapResponseSource());
    }

    public RabbitClient(Destination destination, Queue replyQueue, ResponseSource source) {
        this.destination = destination;
        this.listenQueue = replyQueue;
        this.callbackSource = source;
    }


    @Override
    public FutureMessage send(Message message) throws RabbitException {
        try {
            FutureMessage observable = new RabbitFutureMessage();
            String correlationId = message.properties().property(PropertyKey.CORRELATION_ID);
            callbackSource.save(correlationId, observable);
            listenQueue.subscribe(callbackSource::runCallback);
            destination.send(message);
            return observable;
        } catch (EmptyCorrelationId emptyCorrelationId) {
            throw new RabbitException(emptyCorrelationId);
        }
    }

    @Override
    public void publish(Message message) throws RabbitException {
        destination.send(message);
    }

}

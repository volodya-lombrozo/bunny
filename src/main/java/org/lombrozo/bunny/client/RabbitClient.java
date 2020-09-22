package org.lombrozo.bunny.client;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.domain.queue.NamedQueue;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.message.PropertyKey;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.message.FutureMessage;
import org.lombrozo.bunny.message.RabbitFutureMessage;


public class RabbitClient implements Client {

    private final Destination destination;
    private final Queue listenQueue;
    private final ResponseSource source;

    public RabbitClient(Connection connection, String destinationQueue, String replyQueue) {
        this(new NamedQueue(destinationQueue, connection), new NamedQueue(replyQueue, connection),
                new MapResponseSource());
    }

    public RabbitClient(Destination destination, Queue replyQueue) {
        this(destination, replyQueue, new MapResponseSource());
    }

    public RabbitClient(Destination destination, Queue replyQueue, ResponseSource source) {
        this.destination = destination;
        this.listenQueue = replyQueue;
        this.source = source;
    }

    @Override
    public FutureMessage send(Message message) throws RabbitException {
        FutureMessage observable = new RabbitFutureMessage();
        String correlationId = message.properties().property(PropertyKey.CORRELATION_ID);
        source.save(correlationId, observable);
        listenQueue.subscribe(source::runCallback);
        destination.send(message);
        return observable;
    }

    @Override
    public void publish(Message message) throws RabbitException {
        destination.send(message);
    }

}

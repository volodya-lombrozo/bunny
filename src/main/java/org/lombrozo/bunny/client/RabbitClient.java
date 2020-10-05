package org.lombrozo.bunny.client;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.domain.queue.NamedQueue;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.message.*;
import org.lombrozo.bunny.message.properties.Properties;
import org.lombrozo.bunny.message.properties.PropertyKey;
import org.lombrozo.bunny.util.exceptions.EmptyCorrelationId;
import org.lombrozo.bunny.util.exceptions.EmptyReplyToProperty;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.util.subscription.Subscription;


public class RabbitClient implements Client {

    private final Queue replyQueue;
    private final ResponseSource callbackSource;
    private volatile Subscription subscription;

    public RabbitClient(Connection connection, String replyQueue) {
        this(new NamedQueue(connection, replyQueue), new MapResponseSource());
    }

    public RabbitClient(Queue replyQueue) {
        this(replyQueue, new MapResponseSource());
    }

    public RabbitClient(Queue replyQueue, ResponseSource source) {
        this.replyQueue = replyQueue;
        this.callbackSource = source;
    }

    @Override
    public MessagePipeline sendPipeline(Destination destination, Message message) throws RabbitException {
        try {
            checkRequiredProperties(message);
            subscribeToReplyQueueIfNeeded();
            String correlationId = message.properties().property(PropertyKey.CORRELATION_ID);
            MessagePipeline observable = new RabbitMessagePipeline(destination);
            callbackSource.save(correlationId, observable);
            return observable;
        } catch (EmptyCorrelationId | EmptyReplyToProperty e) {
            throw new RabbitException(e);
        }
    }

    public void cancelSubscription() throws RabbitException {
        subscription.interrupt();
    }

    private synchronized void subscribeToReplyQueueIfNeeded() throws RabbitException {
        if (subscription == null)
            subscription = replyQueue.subscribe(callbackSource::runCallback);
    }

    private void checkRequiredProperties(Message message) throws EmptyCorrelationId, EmptyReplyToProperty {
        Properties properties = message.properties();
        if (!properties.containsProperty(PropertyKey.CORRELATION_ID)) throw new EmptyCorrelationId();
        if (!properties.containsProperty(PropertyKey.REPLY_TO)) throw new EmptyReplyToProperty();
    }
}

package org.lombrozo.bunny.connection;

import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.domain.exchange.Exchange;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.function.Work;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.HashMap;
import java.util.Map;

public class TestChannel implements Channel {

    private final Map<String, Work> listenQueues = new HashMap<>();

    @Override
    public void listenQueue(Queue queue, Work work) {
        listenQueues.put(key(queue), work);
    }

    @Override
    public void publish(Destination rabbitDestination, Message message) throws RabbitException {
        String key = key(rabbitDestination);
        if (listenQueues.containsKey(key))
            listenQueues.get(key).doWork(message);
    }

    @Override
    public void create(Queue queue) {

    }

    @Override
    public void create(Exchange exchange) {

    }

    private String key(Destination destination) {
        return destination.exchangeName() + "." + destination.routingKey();
    }
}

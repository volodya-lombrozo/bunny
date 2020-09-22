package org.lombrozo.bunny.connection;

import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.domain.exchange.Exchange;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.function.Work;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.message.ReplyToDestination;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class TestChannel implements Channel {

    private final Map<String, BlockingDeque<Message>> queuesMap;

    public TestChannel() {
        this(new HashMap<>());
    }

    public TestChannel(Map<String, BlockingDeque<Message>> queuesMap) {
        this.queuesMap = queuesMap;
    }

    @Override
    public void listenQueue(Queue queue, Work work) throws RabbitException {
        try {
            String key = key(queue);
            Message message = queueByKey(key).take();
            work.doWork(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void publish(Destination rabbitDestination, Message message) {
        String key = key(rabbitDestination);
        queueByKey(key).add(message);
    }

    @Override
    public void create(Queue queue) {
        initInMemoryQueue(new ReplyToDestination(queue).value());
    }

    @Override
    public void create(Exchange exchange) {
        initInMemoryQueue(new ReplyToDestination(exchange).value());
    }

    private BlockingDeque<Message> queueByKey(String key) {
        initInMemoryQueue(key);
        return queuesMap.get(key);
    }

    private void initInMemoryQueue(String key) {
        if (!queuesMap.containsKey(key))
            queuesMap.put(key, new LinkedBlockingDeque<>());
    }

    private String key(Destination destination) {
        return destination.exchangeName() + "." + destination.routingKey();
    }
}

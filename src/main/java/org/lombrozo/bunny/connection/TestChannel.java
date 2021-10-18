package org.lombrozo.bunny.connection;

import org.lombrozo.bunny.connection.subscription.Consumer;
import org.lombrozo.bunny.domain.binding.ExchangeBinding;
import org.lombrozo.bunny.domain.binding.QueueBinding;
import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.domain.destination.QueueDestination;
import org.lombrozo.bunny.domain.exchange.Exchange;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.util.subscription.LatchSubscription;
import org.lombrozo.bunny.util.subscription.Subscription;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class TestChannel implements Channel {

    private final Map<String, BlockingDeque<Message>> queuesMap;
    private final ExecutorService listenService = Executors.newFixedThreadPool(2);
    private final ExecutorService publishService = Executors.newFixedThreadPool(2);

    public TestChannel() {
        this(new HashMap<>());
    }

    public TestChannel(Map<String, BlockingDeque<Message>> queuesMap) {
        this.queuesMap = queuesMap;
    }

    @Override
    public Subscription listenQueue(Queue queue, Consumer consumer) {
        listenService.submit(() -> submitListenCommand(queue, consumer));
        return new LatchSubscription();
    }

    @Override
    public void publish(Destination rabbitDestination, Message message) {
        publishService.submit(() -> submitPublishCommand(rabbitDestination, message));
    }

    private void submitListenCommand(Queue queue, Consumer consumer) {
        try {
            QueueDestination destination = new QueueDestination(queue);
            String key = key(destination);
            BlockingDeque<Message> deque = queueByKey(key);
            Message message = deque.take();
            consumer.doWork(message);
        } catch (InterruptedException | RabbitException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void submitPublishCommand(Destination rabbitDestination, Message message) {
        String key = key(rabbitDestination);
        BlockingDeque<Message> deque = queueByKey(key);
        deque.add(message);
    }

    @Override
    public void declare(Queue queue) {
        initInMemoryQueue(key(new QueueDestination(queue)));
    }

    @Override
    public void declare(Exchange exchange) {
    }

    @Override
    public void declare(QueueBinding ignore) {

    }

    @Override
    public void declare(ExchangeBinding ignore) {

    }

    private BlockingDeque<Message> queueByKey(String key) {
        initInMemoryQueue(key);
        return queuesMap.get(key);
    }

    private synchronized void initInMemoryQueue(String key) {
        if (!queuesMap.containsKey(key))
            queuesMap.put(key, new LinkedBlockingDeque<>());
    }

    private String key(Destination destination) {
        return destination.exchangeName() + "." + destination.routingKey();
    }
}

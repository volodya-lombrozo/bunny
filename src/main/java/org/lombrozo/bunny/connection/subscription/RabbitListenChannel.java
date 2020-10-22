package org.lombrozo.bunny.connection.subscription;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import org.lombrozo.bunny.connection.subscription.consumer.NoAckRabbitConsumer;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.function.IgnoreWork;
import org.lombrozo.bunny.function.Work;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.util.subscription.RabbitSubscription;
import org.lombrozo.bunny.util.subscription.Subscription;

import java.io.IOException;

public class RabbitListenChannel implements ListenChannel {

    private final Channel channel;
    private final DefaultConsumer consumer;

    public RabbitListenChannel(Channel channel) {
        this(channel, new NoAckRabbitConsumer(channel, new IgnoreWork()));
    }

    public RabbitListenChannel(Channel channel, DefaultConsumer consumer) {
        this.channel = channel;
        this.consumer = consumer;
    }

    @Override
    public Subscription listenQueue(Queue queue, Work work) throws RabbitException {
        try {
            String consumerTag = channel.basicConsume(queue.name(), true, consumer);
            return new RabbitSubscription(channel, consumerTag);
        } catch (IOException e) {
            throw new RabbitException(e);
        }
    }
}

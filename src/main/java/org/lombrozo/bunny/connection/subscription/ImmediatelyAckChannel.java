package org.lombrozo.bunny.connection.subscription;

import com.rabbitmq.client.Channel;
import org.lombrozo.bunny.connection.subscription.consumer.NoAckConsumer;
import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.function.Work;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.util.subscription.RabbitSubscription;
import org.lombrozo.bunny.util.subscription.Subscription;

import java.io.IOException;

public class ImmediatelyAckChannel implements ListenChannel {

    private final Channel channel;

    public ImmediatelyAckChannel(Channel channel) {
        this.channel = channel;
    }

    @Override
    public Subscription listenQueue(Queue queue, Work work) throws RabbitException {
        try {
            NoAckConsumer callback = new NoAckConsumer(channel, work);
            String consumerTag = channel.basicConsume(queue.name(), false, callback);
            return new RabbitSubscription(channel, consumerTag);
        } catch (IOException e) {
            throw new RabbitException(e);
        }
    }
}

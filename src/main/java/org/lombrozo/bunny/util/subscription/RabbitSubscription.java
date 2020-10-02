package org.lombrozo.bunny.util.subscription;


import com.rabbitmq.client.Channel;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.io.IOException;

public class RabbitSubscription implements Subscription {

    private final Channel channel;
    private final String consumerTag;

    public RabbitSubscription(Channel channel, String consumerTag) {
        this.channel = channel;
        this.consumerTag = consumerTag;
    }

    @Override
    public void await() {
    }

    @Override
    public void interrupt() throws RabbitException {
        try {
            channel.basicCancel(consumerTag);
        } catch (IOException e) {
            throw new RabbitException(e);
        }
    }
}

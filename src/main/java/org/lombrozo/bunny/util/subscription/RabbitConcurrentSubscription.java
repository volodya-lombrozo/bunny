package org.lombrozo.bunny.util.subscription;

import com.rabbitmq.client.Channel;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class RabbitConcurrentSubscription implements Subscription {

    private final List<Channel> channels;
    private final List<String> consumerTags;

    public RabbitConcurrentSubscription() {
        this(new LinkedList<>(), new LinkedList<>());
    }

    public RabbitConcurrentSubscription(List<Channel> channels, List<String> consumerTags) {
        this.channels = channels;
        this.consumerTags = consumerTags;
    }

    public void addChannel(Channel channel, String tag) {
        channels.add(channel);
        consumerTags.add(tag);
    }

    @Override
    public void await() {
    }

    @Override
    public void interrupt() throws RabbitException {
        for (int i = 0; i < channels.size(); i++) {
            try {
                channels.get(i).basicCancel(consumerTags.get(i));
            } catch (IOException e) {
                throw new RabbitException(e);
            }
        }
    }
}

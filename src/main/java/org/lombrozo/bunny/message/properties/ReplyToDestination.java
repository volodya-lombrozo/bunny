package org.lombrozo.bunny.message.properties;

import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.domain.destination.DotReplyToFormatStrategy;
import org.lombrozo.bunny.domain.destination.QueueDestination;
import org.lombrozo.bunny.domain.destination.ReplyToFormatStrategy;
import org.lombrozo.bunny.domain.queue.Queue;

public class ReplyToDestination implements ReplyProperty {


    private final Destination destination;
    private final ReplyToFormatStrategy strategy;

    public ReplyToDestination(Queue queue) {
        this(new QueueDestination(queue));
    }

    public ReplyToDestination(Destination destination) {
        this(destination, new DotReplyToFormatStrategy());
    }

    public ReplyToDestination(Destination destination, ReplyToFormatStrategy strategy) {
        this.destination = destination;
        this.strategy = strategy;
    }

    @Override
    public PropertyKey key() {
        return PropertyKey.REPLY_TO;
    }

    @Override
    public String value() {
        return strategy.format(destination);
    }

    @Override
    public boolean isNotEmpty() {
        return destination != null;
    }
}

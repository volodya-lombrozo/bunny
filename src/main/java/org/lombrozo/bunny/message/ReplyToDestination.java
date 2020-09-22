package org.lombrozo.bunny.message;

import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.domain.destination.DotReplyToFormatStrategy;
import org.lombrozo.bunny.domain.destination.ReplyToFormatStrategy;

public class ReplyToDestination implements Property {


    private final Destination destination;
    private final ReplyToFormatStrategy strategy;

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
}
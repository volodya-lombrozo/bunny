package org.lombrozo.bunny.domain.destination;

public class DotReplyToFormatStrategy implements ReplyToFormatStrategy {
    @Override
    public String format(Destination destination) {
        return destination.exchangeName() + "." + destination.routingKey();
    }
}

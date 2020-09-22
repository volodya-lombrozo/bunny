package org.lombrozo.bunny.domain.destination;

public interface ReplyParsingStrategy {

    String exchange(String rawString);

    String routingKey(String rawString);
}

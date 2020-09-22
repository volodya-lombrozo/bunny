package org.lombrozo.bunny.domain.destination;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;


public class ReplyDestination implements Destination {

    private final String rawString;
    private final ReplyParsingStrategy parsingStrategy;
    private final Connection connection;

    public ReplyDestination(String rawString, Connection connection) {
        this(rawString, new DotParsingStrategy(), connection);
    }

    public ReplyDestination(String rawString, ReplyParsingStrategy parsingStrategy, Connection connection) {
        this.rawString = rawString;
        this.parsingStrategy = parsingStrategy;
        this.connection = connection;
    }

    @Override
    public String exchangeName() {
        return parsingStrategy.exchange(rawString);
    }

    @Override
    public String routingKey() {
        return parsingStrategy.routingKey(rawString);
    }

    @Override
    public void send(Message message) throws RabbitException {
        connection.channel().publish(this, message);
    }

}

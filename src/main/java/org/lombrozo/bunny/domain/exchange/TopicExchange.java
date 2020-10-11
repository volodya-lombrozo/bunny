package org.lombrozo.bunny.domain.exchange;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class TopicExchange implements Exchange {

    private final Exchange exchange;

    public TopicExchange(Connection connection, String name) {
        this(new NamedExchange(connection, name, ExchangeType.TOPIC));
    }

    public TopicExchange(Connection connection, String name, ExchangeDescription description) {
        this(new NamedExchange(connection, name, ExchangeType.TOPIC, description));
    }

    public TopicExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public String name() {
        return exchange.name();
    }

    @Override
    public void declare() throws RabbitException {
        exchange.declare();
    }

    @Override
    public ExchangeType type() {
        return ExchangeType.TOPIC;
    }

    @Override
    public void send(Message message) throws RabbitException {
        exchange.send(message);
    }

    @Override
    public ExchangeDescription description() {
        return exchange.description();
    }


}

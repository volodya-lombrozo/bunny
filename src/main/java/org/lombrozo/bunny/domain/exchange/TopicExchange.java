package org.lombrozo.bunny.domain.exchange;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class TopicExchange implements Exchange {

    private final Exchange exchange;

    public TopicExchange(String name, Connection connection) {
        this(name, "", connection);
    }

    public TopicExchange(String name, String routingKey, Connection connection) {
        this(new NamedExchange(name, routingKey, ExchangeType.TOPIC, connection));
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
    public String exchangeName() {
        return exchange.name();
    }

    @Override
    public String routingKey() {
        return exchange.routingKey();
    }

    @Override
    public void send(Message message) throws RabbitException {
        exchange.send(message);
    }


}

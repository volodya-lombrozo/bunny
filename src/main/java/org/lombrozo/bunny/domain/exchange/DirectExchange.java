package org.lombrozo.bunny.domain.exchange;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class DirectExchange implements Exchange {

    private final Exchange exchange;

    public DirectExchange(String name, Connection connection) {
        this(new NamedExchange(name, ExchangeType.DIRECT, connection));
    }

    public DirectExchange(Exchange exchange) {
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
        return ExchangeType.DIRECT;
    }

    @Override
    public void send(String routingKey, Message message) throws RabbitException {
        exchange.send(routingKey, message);
    }

    @Override
    public void send(Message message) throws RabbitException {
        exchange.send(message);
    }
}

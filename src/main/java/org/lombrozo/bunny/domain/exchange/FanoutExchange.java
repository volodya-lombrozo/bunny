package org.lombrozo.bunny.domain.exchange;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class FanoutExchange implements Exchange {

    private final Exchange exchange;

    public FanoutExchange(Connection connection, String name) {
        this(new NamedExchange(connection, name, ExchangeType.FANOUT));
    }

    public FanoutExchange(Connection connection, String name, ExchangeDescription description) {
        this(new NamedExchange(connection, name, ExchangeType.FANOUT, description));
    }

    public FanoutExchange(Exchange exchange) {
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
        return ExchangeType.FANOUT;
    }

    @Override
    public void send(String routingKey, Message message) throws RabbitException {
        exchange.send(routingKey, message);
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

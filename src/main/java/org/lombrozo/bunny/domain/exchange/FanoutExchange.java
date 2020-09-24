package org.lombrozo.bunny.domain.exchange;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class FanoutExchange implements Exchange {

    private final Exchange exchange;

    public FanoutExchange(String name, Connection connection){
        this(name, "", connection);
    }

    public FanoutExchange(String name, String routingKey, Connection connection) {
        this(new NamedExchange(name, routingKey, ExchangeType.FANOUT, connection));
    }

    public FanoutExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public String name() {
        return exchange.name();
    }

    @Override
    public void create() throws RabbitException {
        exchange.create();
    }

    @Override
    public ExchangeType type() {
        return ExchangeType.FANOUT;
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

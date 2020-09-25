package org.lombrozo.bunny.domain.exchange;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class NamedExchange implements Exchange {

    private final String name;
    private final String routingKey;
    private final ExchangeType type;
    private final Connection connection;


    public NamedExchange(String name, ExchangeType type, Connection connection) {
        this(name, "", type, connection);
    }

    public NamedExchange(String name, String routingKey, ExchangeType type, Connection connection) {
        this.name = name;
        this.routingKey = routingKey;
        this.type = type;
        this.connection = connection;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void declare() throws RabbitException {
        connection.channel().create(this);
    }

    @Override
    public ExchangeType type() {
        return type;
    }

    @Override
    public String exchangeName() {
        return name;
    }

    @Override
    public String routingKey() {
        return routingKey;
    }

    @Override
    public void send(Message message) throws RabbitException {
        connection.channel().publish(this, message);
    }
}

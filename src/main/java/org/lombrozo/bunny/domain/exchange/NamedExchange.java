package org.lombrozo.bunny.domain.exchange;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class NamedExchange implements Exchange {

    private final String name;
    private final ExchangeType type;
    private final Connection connection;

    public NamedExchange(String name, ExchangeType type, Connection connection) {
        this.name = name;
        this.type = type;
        this.connection = connection;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void create() throws RabbitException {
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
        return "";
    }

    @Override
    public void send(Message message) throws RabbitException {
        connection.channel().publish(this, message);
    }
}

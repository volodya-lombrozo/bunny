package org.lombrozo.bunny.domain.exchange;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.domain.destination.ExchangeDestination;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class NamedExchange implements Exchange {

    private final String name;
    private final ExchangeType type;
    private final Connection connection;
    private final ExchangeDescription description;

    public NamedExchange(Connection connection, String name, ExchangeType type) {
        this(connection, name, type, new ExchangeDescription.Default());
    }

    public NamedExchange(Connection connection, String name, ExchangeType type, ExchangeDescription description) {
        this.name = name;
        this.type = type;
        this.connection = connection;
        this.description = description;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public void declare() throws RabbitException {
        connection.channel().declare(this);
    }

    @Override
    public ExchangeType type() {
        return type;
    }

    @Override
    public void send(Message message) throws RabbitException {
        connection.channel().publish(new ExchangeDestination(this, message.routingKey()), message);
    }

    @Override
    public ExchangeDescription description() {
        return description;
    }

}

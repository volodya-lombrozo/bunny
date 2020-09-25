package org.lombrozo.bunny.domain.binding;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.domain.exchange.Exchange;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class ExchangeBinding implements Binding {

    private final Exchange from;
    private final Exchange to;
    private final String routingKey;
    private final Connection connection;

    public ExchangeBinding(Exchange from, Exchange to, String routingKey, Connection connection) {
        this.from = from;
        this.to = to;
        this.routingKey = routingKey;
        this.connection = connection;
    }

    @Override
    public String source() {
        return from.name();
    }

    @Override
    public String destination() {
        return to.name();
    }

    @Override
    public String routingKey() {
        return routingKey;
    }

    @Override
    public void declare() throws RabbitException {
        connection.channel().create(this);
    }
}

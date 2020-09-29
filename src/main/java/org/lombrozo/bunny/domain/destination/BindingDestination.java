package org.lombrozo.bunny.domain.destination;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.domain.binding.Binding;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public class BindingDestination implements Destination {

    private final Binding binding;
    private final Connection connection;

    public BindingDestination(Binding binding, Connection connection) {
        this.binding = binding;
        this.connection = connection;
    }

    @Override
    public String exchangeName() {
        return binding.destination();
    }

    @Override
    public String routingKey() {
        return binding.routingKey();
    }

    @Override
    public void send(Message message) throws RabbitException {
        connection.channel().publish(this, message);
    }
}

package org.lombrozo.bunny.message;

import org.lombrozo.bunny.message.body.Body;
import org.lombrozo.bunny.message.delivery.DeliveryMode;
import org.lombrozo.bunny.message.header.Headers;
import org.lombrozo.bunny.message.properties.*;
import org.lombrozo.bunny.message.routing.RoutingKey;

public class RPCMessage implements Message {

    private final Message message;

    public RPCMessage(Body body, Property... properties) {
        this(new RabbitMessage(body, properties));
    }

    public RPCMessage(String message, Property... properties) {
        this(new RabbitMessage(message, properties));
    }

    public RPCMessage(Body body, Properties properties, Headers headers, DeliveryMode deliveryMode) {
        this(new RabbitMessage(body, properties, headers, deliveryMode));
    }

    public RPCMessage(Body body, Properties properties, Headers headers, DeliveryMode deliveryMode, RoutingKey routingKey) {
        this(new RabbitMessage(body, properties, headers, deliveryMode, routingKey));
    }

    public RPCMessage(Message message) {
        this.message = message;
    }

    @Override
    public RoutingKey routingKey() {
        return message.routingKey();
    }

    @Override
    public DeliveryMode deliveryMode() {
        return message.deliveryMode();
    }

    @Override
    public Headers headers() {
        return message.headers();
    }

    @Override
    public Properties properties() {
        Properties properties = message.properties();
        if (!properties.containsProperty(PropertyKey.CORRELATION_ID))
            properties.put(new CorrelationId());
        return properties;
    }

    @Override
    public Body body() {
        return message.body();
    }
}

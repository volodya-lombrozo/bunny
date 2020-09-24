package org.lombrozo.bunny.message;

public class RPCMessage implements Message {

    private final Message message;

    public RPCMessage(String message, Property... properties) {
        this(new RabbitMessage(message, properties));
    }

    public RPCMessage(Message message) {
        this.message = message;
        message.properties().put(new CorrelationId());
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

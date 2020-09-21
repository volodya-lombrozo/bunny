package org.lombrozo.bunny.message;

public class RabbitMessage implements Message {

    private final DeliveryMode deliveryMode;
    private final Headers headers;
    private final Properties properties;
    private final Body body;

    public RabbitMessage(Properties properties, Body body) {
        this(new EmptyHeaders(), properties, body);
    }

    public RabbitMessage(String message, Property... properties) {
        this(new EmptyHeaders(), new PropertiesSet(properties), new StringBody(message));
    }

    public RabbitMessage(String message, Properties properties) {
        this(new EmptyHeaders(), properties, new StringBody(message));
    }

    public RabbitMessage(String message) {
        this(new StringBody(message));
    }

    public RabbitMessage(Body body) {
        this(new EmptyHeaders(), new EmptyProperties(), body);
    }

    public RabbitMessage(Headers headers, Properties properties, Body body) {
        this(new Persistent(), headers, properties, body);
    }

    public RabbitMessage(DeliveryMode deliveryMode, Headers headers, Properties properties, Body body) {
        this.deliveryMode = deliveryMode;
        this.headers = headers;
        this.properties = properties;
        this.body = body;
    }

    @Override
    public DeliveryMode deliveryMode() {
        return deliveryMode;
    }

    @Override
    public Headers headers() {
        return headers;
    }

    @Override
    public Properties properties() {
        return properties;
    }

    @Override
    public Body body() {
        return body;
    }

    @Override
    public String toString() {
        return "RabbitMessage{" +
                "deliveryMode=" + deliveryMode +
                ", headers=" + headers +
                ", properties=" + properties +
                ", body=" + body +
                '}';
    }
}

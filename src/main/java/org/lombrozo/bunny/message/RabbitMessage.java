package org.lombrozo.bunny.message;

public class RabbitMessage implements Message {

    private final DeliveryMode deliveryMode;
    private final Headers headers;
    private final Properties properties;
    private final Body body;

    public RabbitMessage(Body body, Properties properties) {
        this(body, properties, new EmptyHeaders());
    }

    public RabbitMessage(String message, Property... properties) {
        this(new StringBody(message), new PropertiesSet(properties), new EmptyHeaders());
    }

    public RabbitMessage(String message, Properties properties) {
        this(new StringBody(message), properties, new EmptyHeaders());
    }

    public RabbitMessage(String message) {
        this(new StringBody(message));
    }

    public RabbitMessage(Body body) {
        this(body, new EmptyProperties(), new EmptyHeaders());
    }

    public RabbitMessage(Body body, Properties properties, Headers headers) {
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

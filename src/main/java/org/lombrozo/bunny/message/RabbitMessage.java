package org.lombrozo.bunny.message;

import org.lombrozo.bunny.message.body.Body;
import org.lombrozo.bunny.message.body.StringBody;
import org.lombrozo.bunny.message.delivery.DeliveryMode;
import org.lombrozo.bunny.message.delivery.Persistent;
import org.lombrozo.bunny.message.header.EmptyHeaders;
import org.lombrozo.bunny.message.header.Headers;
import org.lombrozo.bunny.message.properties.*;
import org.lombrozo.bunny.message.routing.EmptyRoutingKey;
import org.lombrozo.bunny.message.routing.RoutingKey;

public class RabbitMessage implements Message {

    private final RoutingKey routingKey;
    private final DeliveryMode deliveryMode;
    private final Headers headers;
    private final Properties properties;
    private final Body body;

    public RabbitMessage(Body body, Properties properties) {
        this(body, properties, new EmptyHeaders());
    }

    public RabbitMessage(Body body, Property... properties) {
        this(body, new PropertiesSet(properties));
    }

    public RabbitMessage(String message, Property... properties) {
        this(new StringBody(message), new PropertiesSet(properties), new EmptyHeaders());
    }

    public RabbitMessage(String message, Properties properties) {
        this(new StringBody(message), properties, new EmptyHeaders());
    }

    public RabbitMessage(Body body, Headers headers) {
        this(body, new EmptyProperties(), headers);
    }

    public RabbitMessage(String message) {
        this(new StringBody(message));
    }

    public RabbitMessage(Body body) {
        this(body, new EmptyProperties(), new EmptyHeaders());
    }

    public RabbitMessage(Body body, Properties properties, Headers headers) {
        this(body, properties, headers, new Persistent(), new EmptyRoutingKey());
    }

    public RabbitMessage(Message message, Property... additional) {
        this(message.body(), message.properties().addAll(additional), message.headers(), message.deliveryMode(), message.routingKey());
    }


    public RabbitMessage(Body body, Properties properties, RoutingKey routingKey) {
        this(body, properties, new EmptyHeaders(), routingKey);
    }

    public RabbitMessage(Body body, RoutingKey routingKey, Property... properties) {
        this(body, new PropertiesSet(properties), routingKey);
    }

    public RabbitMessage(String message, RoutingKey routingKey, Property... properties) {
        this(new StringBody(message), new PropertiesSet(properties), new EmptyHeaders(), routingKey);
    }

    public RabbitMessage(String message, Properties properties, RoutingKey routingKey) {
        this(new StringBody(message), properties, new EmptyHeaders(), routingKey);
    }

    public RabbitMessage(Body body, Headers headers, RoutingKey routingKey) {
        this(body, new EmptyProperties(), headers, routingKey);
    }

    public RabbitMessage(String message, RoutingKey routingKey) {
        this(new StringBody(message), routingKey);
    }

    public RabbitMessage(Body body, RoutingKey routingKey) {
        this(body, new EmptyProperties(), new EmptyHeaders(), routingKey);
    }

    public RabbitMessage(Body body, Properties properties, Headers headers, RoutingKey routingKey) {
        this(body, properties, headers, new Persistent(), routingKey);
    }

    public RabbitMessage(Body body, Properties properties, Headers headers, DeliveryMode deliveryMode) {
        this(body, properties, headers, deliveryMode, new EmptyRoutingKey());
    }

    public RabbitMessage(Body body, Properties properties, Headers headers, DeliveryMode deliveryMode, RoutingKey routingKey) {
        this.deliveryMode = deliveryMode;
        this.headers = headers;
        this.properties = properties;
        this.body = body;
        this.routingKey = routingKey;
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
    public RoutingKey routingKey() {
        return routingKey;
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

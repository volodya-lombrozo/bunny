package org.lombrozo.bunny.message.properties;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.impl.AMQBasicProperties;
import org.lombrozo.bunny.message.delivery.DeliveryMode;
import org.lombrozo.bunny.message.header.Headers;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

public class RabbitProperties implements Properties {

    private final Properties delegate;

    public RabbitProperties(AMQBasicProperties properties) {
        this(new PropertiesSet());
        fillDelegate(properties);
    }

    public RabbitProperties(Properties delegate) {
        this.delegate = delegate;
    }

    @Override
    public String property(PropertyKey key) {
        return delegate.property(key);
    }

    @Override
    public void put(Property property) {
        delegate.put(property);
    }

    @Override
    public boolean containsProperty(PropertyKey key) {
        return delegate.containsProperty(key);
    }

    @Override
    public Properties addAll(Property... additional) {
        return delegate.addAll(additional);
    }

    private void fillDelegate(AMQBasicProperties properties) {
        Arrays.stream(PropertyKey.values())
                .map(keys -> keys.toProperty(properties))
                .filter(Property::isNotEmpty).forEach(delegate::put);
    }

    public AMQP.BasicProperties toAMQPProps(Headers headers, DeliveryMode deliveryMode) {
        AMQP.BasicProperties.Builder builder = new AMQP.BasicProperties.Builder();
        optionalProperty(PropertyKey.TIMESTAMP).map(Long::parseLong).map(Date::new).ifPresent(builder::timestamp);
        optionalProperty(PropertyKey.PRIORITY).map(Integer::parseInt).ifPresent(builder::priority);
        optionalProperty(PropertyKey.CONTENT_TYPE).ifPresent(builder::contentType);
        optionalProperty(PropertyKey.CONTENT_ENCODING).ifPresent(builder::contentEncoding);
        optionalProperty(PropertyKey.CORRELATION_ID).ifPresent(builder::correlationId);
        optionalProperty(PropertyKey.REPLY_TO).ifPresent(builder::replyTo);
        optionalProperty(PropertyKey.EXPIRATION).ifPresent(builder::expiration);
        optionalProperty(PropertyKey.MESSAGE_ID).ifPresent(builder::messageId);
        optionalProperty(PropertyKey.TYPE).ifPresent(builder::type);
        optionalProperty(PropertyKey.USER_ID).ifPresent(builder::userId);
        optionalProperty(PropertyKey.APP_ID).ifPresent(builder::appId);
        optionalProperty(PropertyKey.CLUSTER_ID).ifPresent(builder::clusterId);
        return builder.headers(headers.toMap()).deliveryMode(deliveryMode.toInt()).build();
    }

    private Optional<String> optionalProperty(PropertyKey key) {
        if (containsProperty(key)) {
            return Optional.of(property(key));
        } else return Optional.empty();
    }
}

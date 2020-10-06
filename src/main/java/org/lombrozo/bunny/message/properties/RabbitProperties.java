package org.lombrozo.bunny.message.properties;

import com.rabbitmq.client.impl.AMQBasicProperties;

import java.util.Arrays;

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

}

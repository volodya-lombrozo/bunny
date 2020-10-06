package org.lombrozo.bunny.message.header;

import com.rabbitmq.client.impl.AMQBasicProperties;

import java.util.Map;
import java.util.Optional;

public class RabbitHeaders implements Headers {

    private final Headers delegate;

    public RabbitHeaders(AMQBasicProperties properties) {
        this(new HeadersMap(properties.getHeaders()));
    }

    public RabbitHeaders(Headers delegate) {
        this.delegate = delegate;
    }

    @Override
    public Headers add(Header header) {
        return delegate.add(header);
    }

    @Override
    public Headers add(Headers headers) {
        return delegate.add(headers);
    }

    @Override
    public Optional<Header> header(String key) {
        return delegate.header(key);
    }

    @Override
    public Map<String, Object> toMap() {
        return delegate.toMap();
    }
}

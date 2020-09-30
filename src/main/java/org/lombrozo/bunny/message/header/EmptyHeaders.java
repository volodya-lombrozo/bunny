package org.lombrozo.bunny.message.header;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EmptyHeaders implements Headers {

    @Override
    public String toString() {
        return "EmptyHeaders{}";
    }

    @Override
    public Headers add(Header header) {
        return this;
    }

    @Override
    public Headers add(Headers headers) {
        return this;
    }

    @Override
    public Optional<Header> header(String key) {
        return Optional.of(new Header.Fake());
    }

    @Override
    public Map<String, Object> toMap() {
        return new HashMap<>();
    }
}

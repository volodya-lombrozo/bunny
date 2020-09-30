package org.lombrozo.bunny.message.header;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class HeadersMap implements Headers {

    private final Map<String, Object> headersMap;

    public HeadersMap() {
        this(new HashMap<>());
    }

    public HeadersMap(Header... headers) {
        this(Arrays.stream(headers).collect(Collectors.toMap(Header::key, Header::value)));
    }

    public HeadersMap(Map<String, Object> headersMap) {
        this.headersMap = headersMap;
    }

    @Override
    public Headers add(Header header) {
        headersMap.put(header.key(), header.value());
        return this;
    }

    @Override
    public Headers add(Headers headers) {
        headersMap.putAll(headers.toMap());
        return this;
    }

    @Override
    public Optional<Header> header(String key) {
        if (!headersMap.containsKey(key))
            return Optional.empty();
        Object value = headersMap.get(key);
        String stringValue = String.valueOf(value);
        return Optional.of(new StringHeader(key, stringValue));
    }

    @Override
    public Map<String, Object> toMap() {
        return new HashMap<>(headersMap);
    }
}

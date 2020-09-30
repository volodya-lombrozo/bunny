package org.lombrozo.bunny.message.header;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface Headers {

    Headers add(Header header);

    Headers add(Headers headers);

    Optional<Header> header(String key);

    Map<String, Object> toMap();

    class Fake implements Headers {

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
}

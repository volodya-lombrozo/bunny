package org.lombrozo.bunny.message.header;


import java.util.HashMap;
import java.util.Map;

public interface Headers {

    Headers add(Header header);

    Headers add(Headers headers);

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
        public Map<String, Object> toMap() {
            return new HashMap<>();
        }
    }
}

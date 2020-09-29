package org.lombrozo.bunny.message.header;


import java.util.HashMap;
import java.util.Map;

public interface Headers {

    Headers add(Header header);

    Map<String, Object> toMap();

    class Fake implements Headers {

        @Override
        public Headers add(Header header) {
            return this;
        }

        @Override
        public Map<String, Object> toMap() {
            return new HashMap<>();
        }
    }
}

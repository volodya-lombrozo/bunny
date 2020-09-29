package org.lombrozo.bunny.message.header;


import java.util.HashMap;
import java.util.Map;

public interface Headers {

    void add(Header header);

    Map<String, Object> toMap();

    class Fake implements Headers {

        @Override
        public void add(Header header) {
        }

        @Override
        public Map<String, Object> toMap() {
            return new HashMap<>();
        }
    }
}

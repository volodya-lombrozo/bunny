package org.lombrozo.bunny.message.header;

import java.util.HashMap;
import java.util.Map;

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
    public Map<String, Object> toMap() {
        return new HashMap<>();
    }
}

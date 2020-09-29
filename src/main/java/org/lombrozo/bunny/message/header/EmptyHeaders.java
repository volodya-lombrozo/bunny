package org.lombrozo.bunny.message.header;

import java.util.HashMap;
import java.util.Map;

public class EmptyHeaders implements Headers {

    @Override
    public String toString() {
        return "EmptyHeaders{}";
    }

    @Override
    public void add(Header header) {
    }

    @Override
    public Map<String, Object> toMap() {
        return new HashMap<>();
    }
}

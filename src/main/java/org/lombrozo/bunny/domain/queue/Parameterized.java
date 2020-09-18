package org.lombrozo.bunny.domain.queue;

import java.util.HashMap;
import java.util.Map;

public class Parameterized implements QueueDescription {

    private final QueueDescription description;
    private final Map<String, Object> params;

    public Parameterized() {
        this(new Default());
    }

    public Parameterized(QueueDescription description) {
        this.description = description;
        this.params = new HashMap<>();
    }

    public Parameterized add(String key, Object value) {
        params.put(key, value);
        return this;
    }

    @Override
    public Map<String, Object> params() {
        return params;
    }


    @Override
    public boolean durable() {
        return description.durable();
    }

    @Override
    public boolean exclusive() {
        return description.exclusive();
    }

    @Override
    public boolean autoDelete() {
        return description.autoDelete();
    }
}

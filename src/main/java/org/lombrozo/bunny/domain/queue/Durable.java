package org.lombrozo.bunny.domain.queue;

import java.util.Map;

public class Durable implements QueueDescription {

    private final QueueDescription description;

    public Durable() {
        this(new Default());
    }

    public Durable(QueueDescription description) {
        this.description = description;
    }

    @Override
    public boolean durable() {
        return true;
    }

    @Override
    public boolean exclusive() {
        return description.exclusive();
    }

    @Override
    public boolean autoDelete() {
        return description.autoDelete();
    }

    @Override
    public Map<String, Object> params() {
        return description.params();
    }
}

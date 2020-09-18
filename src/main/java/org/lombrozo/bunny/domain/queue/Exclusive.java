package org.lombrozo.bunny.domain.queue;

import java.util.Map;

public class Exclusive implements QueueDescription {

    private final QueueDescription description;

    public Exclusive() {
        this(new Default());
    }

    public Exclusive(QueueDescription description) {
        this.description = description;
    }

    @Override
    public boolean exclusive() {
        return true;
    }

    @Override
    public boolean durable() {
        return description.durable();
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

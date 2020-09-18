package org.lombrozo.bunny.domain.queue;

import java.util.Map;

public class AutoDelete implements QueueDescription {

    private final QueueDescription description;

    public AutoDelete() {
        this(new Default());
    }

    public AutoDelete(QueueDescription description) {
        this.description = description;
    }

    @Override
    public boolean autoDelete() {
        return true;
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
    public Map<String, Object> params() {
        return description.params();
    }
}

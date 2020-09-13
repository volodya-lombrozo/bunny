package org.lombrozo.bunny.domain;

public class NamedQueue implements Queue {

    private final String queueName;

    public NamedQueue(String queueName) {
        this.queueName = queueName;
    }

    @Override
    public String name() {
        return queueName;
    }
}

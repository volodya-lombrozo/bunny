package org.lombrozo.bunny.domain.exchange;

public class Internal implements ExchangeDescription {

    private final ExchangeDescription description;

    public Internal() {
        this(new Default());
    }

    public Internal(ExchangeDescription description) {
        this.description = description;
    }

    @Override
    public boolean internal() {
        return true;
    }

    @Override
    public boolean autoDelete() {
        return description.autoDelete();
    }

    @Override
    public boolean durable() {
        return description.durable();
    }
}

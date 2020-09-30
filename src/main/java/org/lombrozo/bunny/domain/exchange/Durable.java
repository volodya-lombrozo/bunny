package org.lombrozo.bunny.domain.exchange;

public class Durable implements ExchangeDescription {

    private final ExchangeDescription description;

    public Durable() {
        this(new Default());
    }

    public Durable(ExchangeDescription description) {
        this.description = description;
    }

    @Override
    public boolean internal() {
        return description.internal();
    }

    @Override
    public boolean autoDelete() {
        return description.autoDelete();
    }

    @Override
    public boolean durable() {
        return true;
    }
}

package org.lombrozo.bunny.domain.exchange;

public class AutoDelete implements ExchangeDescription {

    private final ExchangeDescription description;

    public AutoDelete() {
        this(new Default());
    }

    public AutoDelete(ExchangeDescription description) {
        this.description = description;
    }

    @Override
    public boolean internal() {
        return description.internal();
    }

    @Override
    public boolean autoDelete() {
        return true;
    }

    @Override
    public boolean durable() {
        return description.durable();
    }
}

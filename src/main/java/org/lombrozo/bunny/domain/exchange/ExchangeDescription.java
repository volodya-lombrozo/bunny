package org.lombrozo.bunny.domain.exchange;

public interface ExchangeDescription {

    default boolean internal() {
        return false;
    }

    default boolean autoDelete() {
        return false;
    }

    default boolean durable() {
        return false;
    }


    class Fake implements ExchangeDescription {
    }

    class Default implements ExchangeDescription{
    }

}

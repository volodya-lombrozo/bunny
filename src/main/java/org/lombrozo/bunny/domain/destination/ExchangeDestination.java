package org.lombrozo.bunny.domain.destination;

import org.lombrozo.bunny.domain.exchange.Exchange;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.message.routing.RoutingKey;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.Objects;

public class ExchangeDestination implements Destination {

    private final Exchange exchange;
    private final String routingKey;

    public ExchangeDestination(Exchange exchange) {
        this(exchange, "");
    }

    public ExchangeDestination(Exchange exchange, RoutingKey routingKey) {
        this(exchange, routingKey.asString());
    }

    public ExchangeDestination(Exchange exchange, String routingKey) {
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    @Override
    public String exchangeName() {
        return exchange.name();
    }

    @Override
    public String routingKey() {
        return routingKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeDestination that = (ExchangeDestination) o;
        return Objects.equals(exchange, that.exchange) &&
                Objects.equals(routingKey, that.routingKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(exchange, routingKey);
    }

    @Override
    public String toString() {
        return "ExchangeDestination(exchange='" + exchange + "', routingKey='" + routingKey + "')";
    }
}

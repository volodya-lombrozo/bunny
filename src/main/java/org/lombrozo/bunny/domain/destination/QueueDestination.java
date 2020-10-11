package org.lombrozo.bunny.domain.destination;

import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.Objects;

public class QueueDestination implements Destination {

    private final Queue queue;

    public QueueDestination(Queue queue) {
        this.queue = queue;
    }

    @Override
    public String exchangeName() {
        return "";
    }

    @Override
    public String routingKey() {
        return queue.name();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueueDestination that = (QueueDestination) o;
        return Objects.equals(queue, that.queue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(queue);
    }

    @Override
    public String toString() {
        return "QueueDestination{" +
                "queue=" + queue +
                '}';
    }
}

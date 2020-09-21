package org.lombrozo.bunny.domain.queue;

import org.lombrozo.bunny.function.Work;
import org.lombrozo.bunny.destination.Destination;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.util.subscription.Subscription;

public interface Queue extends Destination {

    String name();

    void create() throws RabbitException;

    Subscription subscribe(Work work) throws RabbitException;

    default QueueDescription description(){
        return new QueueDescription.Default();
    }

}

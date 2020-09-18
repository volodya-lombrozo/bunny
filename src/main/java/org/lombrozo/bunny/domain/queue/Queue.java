package org.lombrozo.bunny.domain.queue;

import org.lombrozo.bunny.consumer.work.Work;
import org.lombrozo.bunny.destination.Destination;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.util.subscription.Subscription;


public interface Queue extends Destination {

    String name();

    Subscription subscribe(Work work) throws RabbitException;

}

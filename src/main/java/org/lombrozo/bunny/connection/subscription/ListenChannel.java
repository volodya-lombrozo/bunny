package org.lombrozo.bunny.connection.subscription;

import org.lombrozo.bunny.domain.queue.Queue;
import org.lombrozo.bunny.function.Work;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.util.subscription.Subscription;

public interface ListenChannel {

    Subscription listenQueue(Queue queue, Work work) throws RabbitException;

}

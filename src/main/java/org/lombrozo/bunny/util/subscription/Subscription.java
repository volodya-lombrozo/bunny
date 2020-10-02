package org.lombrozo.bunny.util.subscription;

import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Subscription {

    void await() throws InterruptedException;

    void interrupt() throws RabbitException;

}

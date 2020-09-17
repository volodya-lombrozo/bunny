package org.lombrozo.bunny.consumer;

import org.lombrozo.bunny.util.subscription.LatchSubscription;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Consumer {

    LatchSubscription startListening() throws RabbitException;

}

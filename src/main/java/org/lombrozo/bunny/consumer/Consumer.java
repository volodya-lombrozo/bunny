package org.lombrozo.bunny.consumer;

import org.lombrozo.bunny.util.subscriptions.LatchSubscription;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Consumer {

    LatchSubscription startListening() throws RabbitException;

}

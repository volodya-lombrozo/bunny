package org.lombrozo.bunny.consumer;

import org.lombrozo.bunny.function.Handler;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface ResponsibleConsumer {

    void subscribe(Handler handler) throws RabbitException;

}

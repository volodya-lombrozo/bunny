package org.lombrozo.bunny.function;

import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Work {

    void doWork(Message message) throws RabbitException;

}

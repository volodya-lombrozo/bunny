package org.lombrozo.bunny.client;

import org.lombrozo.bunny.message.FutureMessage;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.EmptyCorrelationId;

public interface ResponseSource {

    void save(String correlationId, FutureMessage message) throws EmptyCorrelationId;

    void runCallback(Message message);

}

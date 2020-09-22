package org.lombrozo.bunny.client;

import org.lombrozo.bunny.message.FutureMessage;
import org.lombrozo.bunny.message.Message;

public interface ResponseSource {

    void save(String correlationId, FutureMessage message);

    void runCallback(Message message);

}

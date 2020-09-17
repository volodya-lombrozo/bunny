package org.lombrozo.bunny.util.subscription;

import org.lombrozo.bunny.message.Message;

public interface ExpectingMessage {

    void doNext(Message message);

    void publish(Message message);

}

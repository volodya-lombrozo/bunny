package org.lombrozo.bunny.util.subscriptions;

import org.lombrozo.bunny.message.Message;

public interface ExpectingMessage {

    void doNext(Message message);

    void publish(Message message);

}

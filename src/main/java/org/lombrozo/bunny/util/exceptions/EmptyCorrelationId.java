package org.lombrozo.bunny.util.exceptions;

import org.lombrozo.bunny.message.Message;

public class EmptyCorrelationId extends Exception {
    public EmptyCorrelationId() {
        super();
    }

    public EmptyCorrelationId(Message message) {
        super("Empty correlation id for message: " + message);
    }
}

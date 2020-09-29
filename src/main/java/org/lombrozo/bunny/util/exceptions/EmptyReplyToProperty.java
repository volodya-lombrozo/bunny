package org.lombrozo.bunny.util.exceptions;

public class EmptyReplyToProperty extends Exception{

    public EmptyReplyToProperty() {
        super("Empty 'reply_to' property");
    }
}

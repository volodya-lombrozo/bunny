package org.lombrozo.bunny.message;

public class ReplyTo implements Property {

    private final String value;

    public ReplyTo() {
        this("");
    }

    public ReplyTo(String destination) {
        this.value = destination;
    }

    @Override
    public PropertyKey key() {
        return PropertyKey.REPLY_TO;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "ReplyTo{" +
                "value='" + value + '\'' +
                '}';
    }
}

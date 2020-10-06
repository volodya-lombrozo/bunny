package org.lombrozo.bunny.message.properties;

public class MessageId implements Property {

    private final String value;

    public MessageId(String value) {
        this.value = value;
    }

    @Override
    public PropertyKey key() {
        return PropertyKey.MESSAGE_ID;
    }

    @Override
    public String value() {
        return value;
    }
}

package org.lombrozo.bunny.message;

public class RabbitMessage implements Message {

    private final byte[] body;

    public RabbitMessage(byte[] body) {
        this.body = body;
    }

    @Override
    public byte[] body() {
        return body;
    }
}

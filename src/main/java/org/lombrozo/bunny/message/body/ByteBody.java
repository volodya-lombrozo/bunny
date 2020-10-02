package org.lombrozo.bunny.message.body;

public class ByteBody implements Body {

    private final byte[] array;

    public ByteBody() {
        this(new byte[0]);
    }

    public ByteBody(byte[] array) {
        this.array = array;
    }

    @Override
    public byte[] toByteArray() {
        return array;
    }

    @Override
    public String toString() {
        return "ByteBody{" + new String(array) + "}";
    }
}

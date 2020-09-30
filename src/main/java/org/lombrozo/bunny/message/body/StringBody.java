package org.lombrozo.bunny.message.body;

import org.lombrozo.bunny.util.RandomString;

public class StringBody implements Body {

    private final String value;

    public StringBody() {
        this(new RandomString().toString());
    }

    public StringBody(String value) {
        this.value = value;
    }

    @Override
    public byte[] toByteArray() {
        return value.getBytes();
    }
}

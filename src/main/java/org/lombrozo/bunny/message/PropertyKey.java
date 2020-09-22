package org.lombrozo.bunny.message;

public enum PropertyKey {
    CORRELATION_ID("correlation_id"),
    REPLY_TO("reply_to"),
    UNKNOWN("")
    ;
    private final String key;

    PropertyKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }
}

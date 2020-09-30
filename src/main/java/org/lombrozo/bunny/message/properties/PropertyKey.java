package org.lombrozo.bunny.message.properties;

public enum PropertyKey {
    CORRELATION_ID("correlation_id"),
    REPLY_TO("reply_to"),
    TYPE("type"),
    CONTENT_TYPE("content_type"),
    UNKNOWN("");

    private final String key;

    PropertyKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }
}

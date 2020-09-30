package org.lombrozo.bunny.message.header;

public class SpringTypeId implements Header {

    private final String value;

    public SpringTypeId() {
        this("");
    }

    public SpringTypeId(Class<?> type) {
        this(type.getName());
    }

    public SpringTypeId(String value) {
        this.value = value;
    }

    @Override
    public String key() {
        return "__TypeId__";
    }

    @Override
    public String value() {
        return value;
    }
}

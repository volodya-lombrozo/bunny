package org.lombrozo.bunny.message.properties;

public class Priority implements Property {

    private final String value;

    public Priority(Integer integer) {
        this(Integer.toString(integer));
    }

    public Priority(String value) {
        this.value = value;
    }

    @Override
    public PropertyKey key() {
        return PropertyKey.PRIORITY;
    }

    @Override
    public String value() {
        return value;
    }
}

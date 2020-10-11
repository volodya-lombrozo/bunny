package org.lombrozo.bunny.message.properties;

import org.lombrozo.bunny.util.IntString;

public class Priority implements Property {

    private final String value;

    public Priority(Integer integer) {
        this(new IntString(integer).toString());
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

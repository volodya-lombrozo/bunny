package org.lombrozo.bunny.message;

import java.util.Objects;

public class Type implements Property {

    private final String value;

    public Type(Class<?> type) {
        this(type.getName());
    }

    public Type(String value) {
        this.value = value;
    }

    @Override
    public PropertyKey key() {
        return PropertyKey.TYPE;
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String toString() {
        return "type=" + value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return Objects.equals(value, type.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

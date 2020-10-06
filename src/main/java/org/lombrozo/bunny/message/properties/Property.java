package org.lombrozo.bunny.message.properties;

import org.lombrozo.bunny.util.IsNotEmpty;

public interface Property {

    PropertyKey key();

    String value();

    default boolean isNotEmpty() {
        return new IsNotEmpty(value()).check();
    }

    class Empty implements Property {

        @Override
        public PropertyKey key() {
            return PropertyKey.UNKNOWN;
        }

        @Override
        public String value() {
            return "";
        }
    }
}

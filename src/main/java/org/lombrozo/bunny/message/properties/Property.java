package org.lombrozo.bunny.message.properties;

public interface Property {

    PropertyKey key();

    String value();


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

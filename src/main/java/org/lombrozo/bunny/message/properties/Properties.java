package org.lombrozo.bunny.message.properties;


public interface Properties {

    String property(PropertyKey key);

    void put(Property property);

    boolean containsProperty(PropertyKey key);

    Properties addAll(Property... additional);

    class Fake implements Properties {

        @Override
        public String property(PropertyKey key) {
            return "";
        }

        @Override
        public void put(Property property) {
        }

        @Override
        public boolean containsProperty(PropertyKey key) {
            return false;
        }

        @Override
        public Properties addAll(Property... additional) {
            return this;
        }
    }

}

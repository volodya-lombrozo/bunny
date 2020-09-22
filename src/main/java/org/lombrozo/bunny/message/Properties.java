package org.lombrozo.bunny.message;


public interface Properties {

    String property(PropertyKey key);

    void put(Property property);

    class Fake implements Properties {

        @Override
        public String property(PropertyKey key) {
            return "";
        }

        @Override
        public void put(Property property) {
        }
    }

}

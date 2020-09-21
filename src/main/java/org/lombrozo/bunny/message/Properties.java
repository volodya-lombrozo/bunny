package org.lombrozo.bunny.message;


public interface Properties {

    String property(String key);

    void put(Property property);

    class Fake implements Properties {

        @Override
        public String property(String key) {
            return "";
        }

        @Override
        public void put(Property property) {
        }
    }

}

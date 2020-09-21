package org.lombrozo.bunny.message;


public interface Properties {

    String property(String key);

    class Fake implements Properties {

        @Override
        public String property(String key) {
            return "";
        }
    }

}

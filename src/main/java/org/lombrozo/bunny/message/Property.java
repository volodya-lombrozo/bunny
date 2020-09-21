package org.lombrozo.bunny.message;

public interface Property {

    String key();

    String value();


    class Empty implements Property {

        @Override
        public String key() {
            return "";
        }

        @Override
        public String value() {
            return "";
        }
    }
}

package org.lombrozo.bunny.message.header;

public interface Header {

    String key();

    String value();

    class Fake implements Header {

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

package org.lombrozo.bunny.message;

public interface Message {

    byte[] body();


    class Fake implements Message{

        @Override
        public byte[] body() {
            return new byte[0];
        }
    }
}

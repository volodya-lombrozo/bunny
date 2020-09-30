package org.lombrozo.bunny.message.body;

public interface Body {

    byte[] toByteArray();


    class Fake implements Body{

        @Override
        public byte[] toByteArray() {
            return new byte[0];
        }
    }
}

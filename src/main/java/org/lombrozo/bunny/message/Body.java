package org.lombrozo.bunny.message;

public interface Body {

    byte[] toByteArray();


    class Fake implements Body{

        @Override
        public byte[] toByteArray() {
            return new byte[0];
        }
    }
}

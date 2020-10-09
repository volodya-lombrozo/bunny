package org.lombrozo.bunny.message.delivery;

public interface DeliveryMode {

    int toInt();

    class Fake implements DeliveryMode {
        @Override
        public String toString() {
            return "FakeDeliveryMode{}";
        }

        @Override
        public int toInt() {
            return -1;
        }
    }
}

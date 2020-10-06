package org.lombrozo.bunny.message.delivery;

public interface DeliveryMode {

    class Fake implements DeliveryMode {
        @Override
        public String toString() {
            return "FakeDeliveryMode{}";
        }
    }
}

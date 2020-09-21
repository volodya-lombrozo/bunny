package org.lombrozo.bunny.message;

public interface Message {

    DeliveryMode deliveryMode();

    Headers headers();

    Properties properties();

    Body body();


    class Fake implements Message {

        @Override
        public DeliveryMode deliveryMode() {
            return new DeliveryMode.Fake();
        }

        @Override
        public Headers headers() {
            return new Headers.Fake();
        }

        @Override
        public Properties properties() {
            return new Properties.Fake();
        }

        @Override
        public Body body() {
            return new Body.Fake();
        }
    }
}

package org.lombrozo.bunny.message;

import org.lombrozo.bunny.message.body.Body;
import org.lombrozo.bunny.message.header.Headers;
import org.lombrozo.bunny.message.delivery.DeliveryMode;
import org.lombrozo.bunny.message.properties.Properties;

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

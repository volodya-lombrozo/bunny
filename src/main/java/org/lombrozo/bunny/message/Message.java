package org.lombrozo.bunny.message;

import org.lombrozo.bunny.message.body.Body;
import org.lombrozo.bunny.message.header.Headers;
import org.lombrozo.bunny.message.delivery.DeliveryMode;
import org.lombrozo.bunny.message.properties.Properties;
import org.lombrozo.bunny.message.routing.RoutingKey;

public interface Message {

    RoutingKey routingKey();

    DeliveryMode deliveryMode();

    Headers headers();

    Properties properties();

    Body body();

    class Fake implements Message {
        private final RoutingKey routingKey = new RoutingKey.Fake();
        private final DeliveryMode deliveryMode = new DeliveryMode.Fake();
        private final Headers headers = new Headers.Fake();
        private final Properties properties = new Properties.Fake();
        private final Body body = new Body.Fake();

        @Override
        public RoutingKey routingKey() {
            return routingKey;
        }

        @Override
        public DeliveryMode deliveryMode() {
            return deliveryMode;
        }

        @Override
        public Headers headers() {
            return headers;
        }

        @Override
        public Properties properties() {
            return properties;
        }

        @Override
        public Body body() {
            return body;
        }
    }
}

package org.lombrozo.bunny.domain.queue;

import org.lombrozo.bunny.function.Work;
import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.util.subscription.LatchSubscription;
import org.lombrozo.bunny.util.subscription.Subscription;

public interface Queue extends Destination {

    String name();

    void create() throws RabbitException;

    Subscription subscribe(Work work) throws RabbitException;

    default QueueDescription description(){
        return new QueueDescription.Default();
    }



    class Fake implements Queue{

        @Override
        public String name() {
            return "";
        }

        @Override
        public void create() {
        }

        @Override
        public Subscription subscribe(Work work) {
            return new LatchSubscription();
        }

        @Override
        public String exchangeName() {
            return "";
        }

        @Override
        public String routingKey() {
            return "";
        }

        @Override
        public void send(Message ignore) {

        }
    }
}

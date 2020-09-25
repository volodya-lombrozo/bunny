package org.lombrozo.bunny.domain.queue;

import org.lombrozo.bunny.connection.Connection;
import org.lombrozo.bunny.domain.Declarable;
import org.lombrozo.bunny.domain.destination.Destination;
import org.lombrozo.bunny.domain.destination.QueueDestination;
import org.lombrozo.bunny.function.Work;
import org.lombrozo.bunny.message.Message;
import org.lombrozo.bunny.util.exceptions.RabbitException;
import org.lombrozo.bunny.util.subscription.LatchSubscription;
import org.lombrozo.bunny.util.subscription.Subscription;

public interface Queue extends Declarable {

    String name();

    Subscription subscribe(Work work) throws RabbitException;

    void send(Message message) throws RabbitException;

    Destination toDestination();

    default QueueDescription description() {
        return new QueueDescription.Default();
    }


    class Fake implements Queue {

        @Override
        public String name() {
            return "";
        }

        @Override
        public void declare() {
        }

        @Override
        public Subscription subscribe(Work work) {
            return new LatchSubscription();
        }

        @Override
        public void send(Message message) {

        }

        @Override
        public Destination toDestination() {
            return new QueueDestination(this, new Connection.Fake());
        }


    }
}

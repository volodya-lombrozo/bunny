package org.lombrozo.bunny.message;

import java.util.function.Consumer;

public interface FutureMessage {

    FutureMessage thenAccept(Consumer<Message> consumer);

    void register(Message message);

    Message block();

    class Fake implements FutureMessage {

        @Override
        public FutureMessage thenAccept(Consumer<Message> ignore) {
            return this;
        }

        @Override
        public void register(Message ignore) {
        }

        @Override
        public Message block() {
            return new Message.Fake();
        }
    }
}

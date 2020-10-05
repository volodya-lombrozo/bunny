package org.lombrozo.bunny.message;

import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.function.Consumer;

public interface MessagePipeline {

    MessagePipeline thenAccept(Consumer<Message> consumer);

    void register(Message message);

    MessagePipeline send(Message message) throws RabbitException;

    Message block();

    class Fake implements MessagePipeline {

        @Override
        public MessagePipeline thenAccept(Consumer<Message> ignore) {
            return this;
        }

        @Override
        public void register(Message ignore) {
        }

        @Override
        public MessagePipeline send(Message message) {
            return this;
        }

        @Override
        public Message block() {
            return new Message.Fake();
        }
    }
}

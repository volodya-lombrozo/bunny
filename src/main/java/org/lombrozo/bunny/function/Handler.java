package org.lombrozo.bunny.function;

import org.lombrozo.bunny.message.Message;

public interface Handler {

    Message handle(Message message);


    class Echo implements Handler{

        @Override
        public Message handle(Message echo) {
            return echo;
        }
    }
}

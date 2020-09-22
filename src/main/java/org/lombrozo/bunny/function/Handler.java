package org.lombrozo.bunny.function;

import org.lombrozo.bunny.message.Message;

public interface Handler {

    Message handle(Message message);

}

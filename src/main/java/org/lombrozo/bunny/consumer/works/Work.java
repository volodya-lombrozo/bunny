package org.lombrozo.bunny.consumer.works;

import org.lombrozo.bunny.message.Message;

public interface Work {

    void doWork(Message message);

}

package org.lombrozo.bunny.consumer.work;

import org.lombrozo.bunny.message.Message;

public interface Work {

    void doWork(Message message);

}

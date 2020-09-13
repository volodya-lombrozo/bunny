package org.lombrozo.bunny.consumer.targets;

import org.lombrozo.bunny.connections.channels.Channel;
import org.lombrozo.bunny.consumer.works.RunnableWork;
import org.lombrozo.bunny.consumer.works.Work;
import org.lombrozo.bunny.domain.NamedQueue;
import org.lombrozo.bunny.domain.Queue;
import org.lombrozo.bunny.util.exceptions.RabbitException;


public class QueueTarget implements Target {

    private final Queue queue;
    private final Work work;

    public QueueTarget(String queueName, Runnable runnable){
        this(new NamedQueue(queueName), new RunnableWork(runnable));
    }

    public QueueTarget(String queueName, Work work) {
        this(new NamedQueue(queueName), work);
    }

    public QueueTarget(Queue queue, Work work) {
        this.queue = queue;
        this.work = work;
    }

    @Override
    public void listen(Channel channel) throws RabbitException {
        channel.listenQueue(queue, work);
    }
}

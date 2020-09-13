package org.lombrozo.bunny.consumer;


import org.lombrozo.bunny.connections.Connection;
import org.lombrozo.bunny.consumer.targets.Target;
import org.lombrozo.bunny.util.subscriptions.LatchSubscription;
import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class TargetConsumer implements Consumer {

    private final Collection<Target> targets;
    private final Connection connection;

    public TargetConsumer(Connection connection, Target target) {
        this(connection, Collections.singleton(target));
    }

    public TargetConsumer(Connection connection, Target... targets) {
        this(connection, Arrays.asList(targets));
    }

    public TargetConsumer(Connection connection, Collection<Target> targets) {
        this.connection = connection;
        this.targets = targets;
    }

    @Override
    public LatchSubscription startListening() throws RabbitException {
        for (Target target : targets)
            target.listen(connection.channel());
        return new LatchSubscription();
    }
}

package org.lombrozo.bunny.domain.queue;

import java.util.Collections;
import java.util.Map;

public interface QueueDescription {

    default boolean durable() {
        return false;
    }

    default boolean exclusive() {
        return false;
    }

    default boolean autoDelete() {
        return false;
    }

    default Map<String, Object> params() {
        return Collections.emptyMap();
    }


    class Default implements QueueDescription{
    }
}

package org.lombrozo.bunny.domain;

import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Declarable {
    void declare() throws RabbitException;
}

package org.lombrozo.bunny.domain;

import org.lombrozo.bunny.util.exceptions.RabbitException;

public interface Topology {

    Topology register(Declarable declarable);

    void createAll() throws RabbitException;

}

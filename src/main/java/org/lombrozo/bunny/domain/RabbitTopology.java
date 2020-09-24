package org.lombrozo.bunny.domain;

import org.lombrozo.bunny.util.exceptions.RabbitException;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RabbitTopology implements Topology {

    private final List<Declarable> declarableCollection;

    public RabbitTopology() {
        this(new LinkedList<>());
    }

    public RabbitTopology(List<Declarable> declarable) {
        this.declarableCollection = declarable;
    }

    @Override
    public Topology register(Declarable... declarable) {
        declarableCollection.addAll(Arrays.asList(declarable));
        return this;
    }

    @Override
    public Topology register(Declarable declarable) {
        declarableCollection.add(declarable);
        return this;
    }

    @Override
    public void createAll() throws RabbitException {
        for (Declarable declarable : declarableCollection)
            declarable.create();
    }
}

package edu.uj.po.simulation.interfaces;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractComponent {
    private static final AtomicInteger counter = new AtomicInteger();
    protected final int globalId;

    public int getGlobalId() {
        return globalId;
    }

    public AbstractComponent() {
        super();
        this.globalId = counter.incrementAndGet();
    }
}

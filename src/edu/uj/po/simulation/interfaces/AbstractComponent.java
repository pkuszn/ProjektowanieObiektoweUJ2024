package edu.uj.po.simulation.interfaces;

import edu.uj.po.simulation.recorder.ComponentState;
import edu.uj.po.simulation.recorder.ComponentStateRecorder;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AbstractComponent {
    private static final AtomicInteger counter = new AtomicInteger();
    protected final int globalId;
    private final ComponentStateRecorder recorder;

    public AbstractComponent() {
        super();
        this.globalId = counter.incrementAndGet();
        this.recorder = ComponentStateRecorder.getInstance();
    }

    public int getGlobalId() {
        return globalId;
    }

    protected void addComponentState(int id, ComponentState componentState) {
        recorder.addComponentState(id, componentState);
    }
    
    protected void addComponentStates(Map<Integer, ComponentState> componentStatesMap) {
        recorder.addComponentStates(componentStatesMap);
    }
}

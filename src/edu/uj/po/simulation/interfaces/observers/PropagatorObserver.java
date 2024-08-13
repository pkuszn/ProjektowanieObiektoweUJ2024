package edu.uj.po.simulation.interfaces.observers;

import edu.uj.po.simulation.interfaces.enums.ComponentBehaviour;

public interface PropagatorObserver {
    void update(ComponentBehaviour behaviour);
}

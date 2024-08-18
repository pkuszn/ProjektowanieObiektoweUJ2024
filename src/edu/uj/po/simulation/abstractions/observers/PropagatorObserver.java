package edu.uj.po.simulation.abstractions.observers;

import edu.uj.po.simulation.enums.ComponentBehaviour;

public interface PropagatorObserver {
    void update(ComponentBehaviour behaviour, int tickNumber);
}

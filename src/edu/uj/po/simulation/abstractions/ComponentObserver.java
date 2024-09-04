package edu.uj.po.simulation.abstractions;

import edu.uj.po.simulation.interfaces.ComponentPinState;

public interface ComponentObserver {
    void update(ComponentPinState state);
}

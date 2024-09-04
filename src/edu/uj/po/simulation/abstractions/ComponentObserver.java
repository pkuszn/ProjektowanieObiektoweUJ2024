package edu.uj.po.simulation.abstractions;

import edu.uj.po.simulation.interfaces.PinState;

public interface ComponentObserver {
    void update(PinState newState);
}

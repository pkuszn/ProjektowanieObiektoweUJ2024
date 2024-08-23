package edu.uj.po.simulation.abstractions;

import edu.uj.po.simulation.abstractions.observers.GateObserver;

public interface LogicGate {
    void setPinState(int pinNumber, boolean value);
    boolean getState();
    void addObserver(GateObserver observer);
    void removeObserver(GateObserver observer);
    void notifyObservers();
}

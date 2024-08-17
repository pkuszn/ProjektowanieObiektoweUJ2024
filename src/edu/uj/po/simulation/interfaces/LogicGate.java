package edu.uj.po.simulation.interfaces;

import edu.uj.po.simulation.interfaces.observers.GateObserver;

public interface LogicGate {
    void setPinState(int pinNumber, boolean value);
    boolean getState();
    void addObserver(GateObserver observer);
    void removeObserver(GateObserver observer);
    void notifyObservers();
}

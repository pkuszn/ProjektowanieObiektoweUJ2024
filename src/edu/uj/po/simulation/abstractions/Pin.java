package edu.uj.po.simulation.abstractions;

import edu.uj.po.simulation.abstractions.observers.ComponentPinObserver;
import edu.uj.po.simulation.enums.PinType;


public interface Pin {
    boolean getPin();
    PinType getPinType();
    void setPin(boolean value);
    void addObserver(ComponentPinObserver observer);
    void removeObserver(ComponentPinObserver observer);
    void notifyObservers();
}

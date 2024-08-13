package edu.uj.po.simulation.interfaces;

import edu.uj.po.simulation.interfaces.enums.PinType;
import edu.uj.po.simulation.interfaces.observers.ComponentPinObserver;

public interface Pin {
    boolean getPin();
    PinType getPinType();
    void setPin(boolean value);
    void addObserver(ComponentPinObserver observer);
    void removeObserver(ComponentPinObserver observer);
    void notifyObservers();
}

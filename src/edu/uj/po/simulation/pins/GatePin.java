package edu.uj.po.simulation.pins;

import edu.uj.po.simulation.interfaces.Pin;
import edu.uj.po.simulation.interfaces.enums.PinType;
import edu.uj.po.simulation.interfaces.observers.ComponentPinObserver;

import java.util.ArrayList;
import java.util.List;

public class GatePin implements Pin {
    private boolean value = false;
    private final List<ComponentPinObserver> observers;

    public GatePin() {
        super();
        observers = new ArrayList<>();
    }

    @Override
    public boolean getPin() {
        return value;
    }
    @Override
    public void setPin(boolean value) {
        this.value = value;
        notifyObservers();
    }
    @Override
    public void addObserver(ComponentPinObserver observer) {
        observers.add(observer);
    }
    @Override
    public void removeObserver(ComponentPinObserver observer) {
        observers.remove(observer);
    }
    @Override
    public void notifyObservers() {
        for (ComponentPinObserver observer : observers) {
            observer.update(this.value);
        } 
    }

    @Override
    public PinType getPinType() {
        return PinType.NONE;
    }
}
package edu.uj.po.simulation;

import java.util.ArrayList;
import java.util.List;

import edu.uj.po.simulation.interfaces.ComponentPinObserver;
import edu.uj.po.simulation.interfaces.Pin;
import edu.uj.po.simulation.interfaces.PinType;

public class GatePin implements Pin {
    private boolean value = false;
    private List<ComponentPinObserver> observers;

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

package edu.uj.po.simulation.pins;

import edu.uj.po.simulation.abstractions.Pin;
import edu.uj.po.simulation.abstractions.observers.ComponentPinObserver;
import edu.uj.po.simulation.enums.PinType;

import java.util.ArrayList;
import java.util.List;

public class ComponentPin implements Pin {
    private boolean value;
    private PinType pinType;
    private final List<ComponentPinObserver> observers;

    public ComponentPin() {
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
    public PinType getPinType() {
        return pinType;
    }

    public void setPinType(PinType pinType) {
        this.pinType = pinType;
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
}

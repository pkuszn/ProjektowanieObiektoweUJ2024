package edu.uj.po.simulation.gates;

import edu.uj.po.simulation.abstractions.LogicGate;
import edu.uj.po.simulation.abstractions.observers.GateObserver;
import edu.uj.po.simulation.pins.GatePin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseGate implements LogicGate {
    protected Map<Integer, GatePin> pins = new HashMap<>();
    protected List<GateObserver> gateObservers = new ArrayList<>();
    public BaseGate() {
        super();
    }

    @Override
    public void setPinState(int pinNumber, boolean value) {
        GatePin pin = pins.get(pinNumber);
        pin.setPin(value);
        notifyObservers();
    }

    @Override
    public boolean getState() {
        throw new UnsupportedOperationException("Unimplemented method 'getState'");
    }

    @Override
    public void addObserver(GateObserver observer) {
        gateObservers.add(observer);
    }

    @Override
    public void removeObserver(GateObserver observer) {
        gateObservers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (GateObserver observer : gateObservers) {
            observer.update(getState());
        }
    }
}

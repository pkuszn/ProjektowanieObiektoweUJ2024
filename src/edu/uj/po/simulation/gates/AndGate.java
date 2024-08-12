package edu.uj.po.simulation.gates;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.uj.po.simulation.interfaces.GateObserver;
import edu.uj.po.simulation.interfaces.LogicGate;
import edu.uj.po.simulation.pins.GatePin;

public class AndGate implements LogicGate {
    private Map<Integer, GatePin> pins = new HashMap<>();
    private List<GateObserver> gateObservers = new ArrayList<>();

    public AndGate(int size) {
        for (int i = 1; i < size + 1; i++) {
            pins.put(i, new GatePin());
        }
    }

    public void setPinState(int pinNumber, boolean value) {
        GatePin pin = pins.get(pinNumber);
        pin.setPin(value);
        notifyObservers();
    }

    @Override
    public boolean getState() {
        boolean state = true;
        for (GatePin pin : pins.values()) {
            if (!pin.getPin()) {
                state = false;
                break;
            }
        }
        return state;
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
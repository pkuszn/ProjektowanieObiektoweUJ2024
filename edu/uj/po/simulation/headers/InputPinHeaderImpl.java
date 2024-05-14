package edu.uj.po.simulation.headers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.uj.po.simulation.ComponentPin;
import edu.uj.po.simulation.interfaces.CircuitObserver;
import edu.uj.po.simulation.interfaces.GateObserver;
import edu.uj.po.simulation.interfaces.InputPinHeader;
import edu.uj.po.simulation.interfaces.InputPinHeaderObserver;
import edu.uj.po.simulation.interfaces.IntegratedCircuit;
import edu.uj.po.simulation.utils.PinGenerator;

public class InputPinHeaderImpl implements InputPinHeader {
    private int id;
    private Map<Integer, ComponentPin> inputs;
    private Map<Integer, List<InputPinHeaderObserver>> observers;

    public InputPinHeaderImpl(int size) {
        super();
        inputs = new HashMap<>();
        observers = new HashMap<>();
        id = PinGenerator.generatePinNumber(0, 10000);
    }

    private ComponentPin getInput(int pinNumber) {
        return inputs.get(pinNumber);
    }

    @Override
    public boolean getPinState(int pinNumber) {
        return inputs.get(pinNumber).getPin();
    }

    @Override
    public void setPinState(int pinNumber, boolean value) {
        ComponentPin pin = inputs.get(pinNumber);
        if (pin == null) {
            System.out.println("Pin not updated");
        }
        pin.setPin(value);
        notifyObservers(pinNumber);
    }

    public void connectIntegratedCircuitToPinHeader(int pinNumber, IntegratedCircuit integratedCircuit) {
        ComponentPin outputComponentPin = getInput(pinNumber);
        integratedCircuit.addObserver(pinNumber, new CircuitObserver() {
            @Override
            public void update(boolean newState) {
                outputComponentPin.setPin(newState);
            }
        });
    }

    @Override
    public void addObserver(int pinNumber, InputPinHeaderObserver observer) {
        List<InputPinHeaderObserver> circuitObservers = observers.get(pinNumber);
        if (circuitObservers == null) {
            circuitObservers = new ArrayList<>();
            circuitObservers.add(observer);
            observers.put(pinNumber, circuitObservers);
        } else {
            circuitObservers.add(observer);
        }
    }

    @Override
    public void removeObserver(int pinNumber, InputPinHeaderObserver observer) {
        List<InputPinHeaderObserver> circuitObservers = observers.get(pinNumber);
        if (observers == null) {
            return;
        }
        circuitObservers.remove(observer);
    }
    
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void notifyObservers(int pinNumber) {
        List<InputPinHeaderObserver> circuitObservers = observers.get(pinNumber);
        if (circuitObservers == null) {
            System.out.println("No observer connected!");
            return;
        }
        boolean state = getPinState(pinNumber);
        for (InputPinHeaderObserver observer : circuitObservers) {
            observer.update(state);
        }
    }    
}

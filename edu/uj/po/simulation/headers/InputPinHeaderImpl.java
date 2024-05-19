package edu.uj.po.simulation.headers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.uj.po.simulation.ComponentPin;
import edu.uj.po.simulation.interfaces.InputPinHeader;
import edu.uj.po.simulation.interfaces.IntegratedCircuit;
import edu.uj.po.simulation.interfaces.Observer;
import edu.uj.po.simulation.interfaces.PinType;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.utils.PinGenerator;

public class InputPinHeaderImpl implements InputPinHeader {
    private int id;
    private Map<Integer, ComponentPin> inputs;
    private Map<Integer, List<Observer>> observers;

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
    public void setPinState(int pinNumber, boolean value) {
        ComponentPin pin = inputs.get(pinNumber);
        if (pin == null) {
            System.out.println("Pin not updated");
        }
        pin.setPin(value);
        notifyObserver(pinNumber);
    }

    private boolean getPinState(int pinNumber) {
        return inputs.get(pinNumber).getPin();
    }

    public void connectIntegratedCircuitToPinHeader(int pinNumber, IntegratedCircuit integratedCircuit) throws UnknownPin {
        ComponentPin outputComponentPin = getInput(pinNumber);
        integratedCircuit.addObserver(pinNumber, new Observer() {
            @Override
            public void update(boolean newState) {
                outputComponentPin.setPin(newState);
            }
        });
    }

    @Override
    public void addObserver(int pinNumber, Observer observer) {
        List<Observer> circuitObservers = observers.get(pinNumber);
        if (circuitObservers == null) {
            circuitObservers = new ArrayList<>();
            circuitObservers.add(observer);
            observers.put(pinNumber, circuitObservers);
        } else {
            circuitObservers.add(observer);
        }
    }

    @Override
    public void removeObserver(int pinNumber, Observer observer) {
        List<Observer> circuitObservers = observers.get(pinNumber);
        if (observers == null) {
            return;
        }
        circuitObservers.remove(observer);
    }
    
    @Override
    public int getGlobalId() {
        return id;
    }

    @Override
    public void notifyObserver(int pinNumber) {
        List<Observer> circuitObservers = observers.get(pinNumber);
        if (circuitObservers == null) {
            System.out.println("No observer connected!");
            return;
        }
        boolean state = getPinState(pinNumber);
        for (Observer observer : circuitObservers) {
            observer.update(state);
        }
    }

    @Override
    public PinType getPinType(int pinNumber) throws UnknownPin {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPinType'");
    }
}

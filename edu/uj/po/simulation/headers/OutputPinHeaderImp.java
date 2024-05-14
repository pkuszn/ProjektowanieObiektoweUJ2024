package edu.uj.po.simulation.headers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.uj.po.simulation.ComponentPin;
import edu.uj.po.simulation.interfaces.OutputPinHeader;
import edu.uj.po.simulation.interfaces.OutputPinHeaderObserver;
import edu.uj.po.simulation.utils.PinGenerator;

public class OutputPinHeaderImp implements OutputPinHeader {
    private int id;
    private Map<Integer, ComponentPin> outputs;
    private Map<Integer, List<OutputPinHeaderObserver>> observers;

    public OutputPinHeaderImp(int size) {
        super();
        outputs = new HashMap<>();
        observers = new HashMap<>();
        id = PinGenerator.generatePinNumber(0, 10000);
        for (int i = 1; i < size; i++) {
            outputs.put(i, new ComponentPin());
        }
    }

    @Override
    public boolean getPinState(int pinNumber) {
        return outputs.get(pinNumber).getPin();
    }

    @Override
    public void setPinState(int pinNumber, boolean value) {
        ComponentPin pin = outputs.get(pinNumber);
        if (pin == null) {
            System.out.println("Pin not updated");
        }
        pin.setPin(value);
        notifyObservers(pinNumber);
    }
    
    @Override
    public void addObserver(int pinNumber, OutputPinHeaderObserver observer) {
        List<OutputPinHeaderObserver> circuitObservers = observers.get(pinNumber);
        if (circuitObservers == null) {
            circuitObservers = new ArrayList<>();
            circuitObservers.add(observer);
            observers.put(pinNumber, circuitObservers);
        } else {
            circuitObservers.add(observer);
        }
    }

    @Override
    public void removeObserver(int pinNumber, OutputPinHeaderObserver observer) {
        List<OutputPinHeaderObserver> circuitObservers = observers.get(pinNumber);
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
        List<OutputPinHeaderObserver> circuitObservers = observers.get(pinNumber);
        if (circuitObservers == null) {
            System.out.println("No observer connected!");
            return;
        }
        boolean state = getPinState(pinNumber);
        for (OutputPinHeaderObserver observer : circuitObservers) {
            observer.update(state);
        }
    }
}

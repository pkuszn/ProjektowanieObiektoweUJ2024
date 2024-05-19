package edu.uj.po.simulation.headers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.uj.po.simulation.ComponentPin;
import edu.uj.po.simulation.interfaces.Observer;
import edu.uj.po.simulation.interfaces.OutputPinHeader;
import edu.uj.po.simulation.interfaces.OutputPinHeaderObserver;
import edu.uj.po.simulation.interfaces.PinType;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.utils.PinGenerator;

public class OutputPinHeaderImpl implements OutputPinHeader {
    private int id;
    private Map<Integer, ComponentPin> outputs;
    private Map<Integer, List<Observer>> observers;

    public OutputPinHeaderImpl(int size) {
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

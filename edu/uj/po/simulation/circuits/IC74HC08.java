package edu.uj.po.simulation.circuits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.uj.po.simulation.ComponentPin;
import edu.uj.po.simulation.interfaces.IntegratedCircuit;
import edu.uj.po.simulation.interfaces.Observer;
import edu.uj.po.simulation.interfaces.PinType;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.utils.PinGenerator;

/*
 * Description: https://eduinf.waw.pl/inf/prg/010_uc/7408.php
 */
public class IC74HC08 implements IntegratedCircuit {
    private Map<Integer, ComponentPin> inputs;
    private Map<Integer, ComponentPin> outputs;
    private Map<Integer, List<Observer>> observers;
    private int globalId;

    public IC74HC08(Integer[] inputPinNumbers, Integer[] outputPinNumbers) {
        super();
        inputs = new HashMap<>();
        outputs = new HashMap<>();
        observers = new HashMap<>();
        
        globalId = PinGenerator.generatePinNumber(1, 10000);

        for (Integer input : inputPinNumbers) {
            inputs.put(input, new ComponentPin());
        }

        for (Integer output : outputPinNumbers) {
            outputs.put(output, new ComponentPin());
        }
    }

    public void setPinState(int pinNumber, boolean value) {
        ComponentPin componentPin = inputs.get(pinNumber);
        componentPin.setPin(value);
        notifyObserver(pinNumber);
    }

    public boolean getPinState(int pinNumber) {
        return outputs.get(pinNumber).getPin();
    }

    @Override
    public PinType getPinType(int pinNumber) throws UnknownPin {
        ComponentPin pinIn = inputs.get(pinNumber);
        ComponentPin pinOut = outputs.get(pinNumber);
        if (pinIn != null) {
            return PinType.IN;
        } else if (pinOut != null) {
            return PinType.OUT;
        } else {
            throw new UnknownPin(globalId, pinNumber);
        }
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
    public int getGlobalId() {
        return this.globalId;
    }

    @Override
    public ComponentPin getOutputPin(int pinNumber) throws UnknownPin {
        ComponentPin pin = inputs.get(pinNumber);
        if (pin == null) {
            throw new UnknownPin(this.globalId, pinNumber);
        }

        return pin;
    }

    @Override
    public ComponentPin getInputPin(int pinNumber) throws UnknownPin {
        ComponentPin pin = outputs.get(pinNumber);
        if (pin == null) {
            throw new UnknownPin(this.globalId, pinNumber);
        }

        return pin;
    }
}

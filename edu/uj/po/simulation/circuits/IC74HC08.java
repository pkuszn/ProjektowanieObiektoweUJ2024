package edu.uj.po.simulation.circuits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.uj.po.simulation.ComponentPin;
import edu.uj.po.simulation.interfaces.CircuitObserver;
import edu.uj.po.simulation.interfaces.IntegratedCircuit;
import edu.uj.po.simulation.interfaces.PinType;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.utils.PinGenerator;

/*
 * Description: https://eduinf.waw.pl/inf/prg/010_uc/7408.php
 */
public class IC74HC08 implements IntegratedCircuit {
    private Map<Integer, ComponentPin> inputs;
    private Map<Integer, ComponentPin> outputs;
    private Map<Integer, List<CircuitObserver>> observers;
    private int globalId;

    public IC74HC08() {
        super();
        inputs = new HashMap<>();
        outputs = new HashMap<>();
        observers = new HashMap<>();
        
        globalId = PinGenerator.generatePinNumber(1, 10000);

        inputs.put(1, new ComponentPin());
        inputs.put(2, new ComponentPin());
        outputs.put(3, new ComponentPin());

        inputs.put(4, new ComponentPin());
        inputs.put(5, new ComponentPin());
        outputs.put(6, new ComponentPin());

        inputs.put(7, new ComponentPin());
        inputs.put(8, new ComponentPin());
        outputs.put(9, new ComponentPin());

        inputs.put(10, new ComponentPin());
        inputs.put(11, new ComponentPin());
        outputs.put(12, new ComponentPin());


        for (int i = 1; i <= 4; i++) {
            outputs.put(i, new ComponentPin());
        }

        for (int i = 1; i <= 8; i++) {
            inputs.put(i, new ComponentPin());
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

    public ComponentPin getOutput(int pinNumber) {
        return outputs.get(pinNumber);
    }

    public ComponentPin getInput(int pinNumber) {
        return inputs.get(pinNumber);
    }

    @Override
    public PinType getPinType(int pinNumber) throws UnknownPin {
        ComponentPin pinIn = inputs.get(pinNumber);
        ComponentPin pinOut = outputs.get(pinNumber);
        if (pinIn != null)
        
    }

    @Override
    public void addObserver(int pinNumber, CircuitObserver observer) {
        List<CircuitObserver> circuitObservers = observers.get(pinNumber);
        if (circuitObservers == null) {
            circuitObservers = new ArrayList<>();
            circuitObservers.add(observer);
            observers.put(pinNumber, circuitObservers);
        } else {
            circuitObservers.add(observer);
        }
    }

    @Override
    public void removeObserver(int pinNumber, CircuitObserver observer) {
        List<CircuitObserver> circuitObservers = observers.get(pinNumber);
        if (observers == null) {
            return;
        }
        circuitObservers.remove(observer);
    }

    @Override
    public void notifyObserver(int pinNumber) {
        List<CircuitObserver> circuitObservers = observers.get(pinNumber);
        if (circuitObservers == null) {
            System.out.println("No observer connected!");
            return;
        }
        boolean state = getPinState(pinNumber);
        for (CircuitObserver observer : circuitObservers) {
            observer.update(state);
        }
    }

    @Override
    public int getGlobalId() {
        return this.globalId;
    }

    private void createPins(Integer[] pinInputs, Integer[] pinOutputs, Integer[] pinNone) {
        
    }
}

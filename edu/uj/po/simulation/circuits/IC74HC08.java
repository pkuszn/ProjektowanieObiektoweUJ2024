package edu.uj.po.simulation.circuits;

import java.util.HashMap;
import java.util.Map;

import edu.uj.po.simulation.ComponentPin;
import edu.uj.po.simulation.interfaces.CircuitObserver;
import edu.uj.po.simulation.interfaces.IntegratedCircuit;

public class IC74HC08 implements IntegratedCircuit {
    private Map<Integer, ComponentPin> inputs = new HashMap<>();
    private Map<Integer, ComponentPin> outputs = new HashMap<>();

    public void setPinState(int pinNumber, boolean value) {
        ComponentPin componentPin = inputs.get(pinNumber);
        componentPin.setPin(value);
    }

    public ComponentPin getOutput(int pinNumber) {
        return outputs.get(pinNumber);
    }


    public IC74HC08() {
        super();
    }


    @Override
    public void addObserver(CircuitObserver observer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addObserver'");
    }

    @Override
    public void removeObserver(CircuitObserver observer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeObserver'");
    }

    @Override
    public void notifyObserver() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notifyObserver'");
    }
    
}

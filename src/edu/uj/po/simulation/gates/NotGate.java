package edu.uj.po.simulation.gates;

import edu.uj.po.simulation.interfaces.LogicGate;
import edu.uj.po.simulation.interfaces.observers.GateObserver;

public class NotGate implements LogicGate {

    public NotGate() {
        super();
    }

    @Override
    public boolean getState() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getOutput'");
    }

    @Override
    public void setPinState(int pinNumber, boolean value) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPinState'");
    }

    @Override
    public void addObserver(GateObserver observer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addObserver'");
    }

    @Override
    public void removeObserver(GateObserver observer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeObserver'");
    }

    @Override
    public void notifyObservers() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'notifyObservers'");
    }
    
}

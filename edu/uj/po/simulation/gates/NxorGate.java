package edu.uj.po.simulation.gates;

import edu.uj.po.simulation.interfaces.GateObserver;
import edu.uj.po.simulation.interfaces.LogicGate;

public class NxorGate implements LogicGate {

    public NxorGate() {
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

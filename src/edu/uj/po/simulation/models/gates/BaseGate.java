package edu.uj.po.simulation.models.gates;

public abstract class BaseGate {
    public BaseGate() {
        super();
    }

    public void setPinState(int pinNumber, boolean value) {
        // pin.setPin(value);
    }

    public boolean getState() {
        throw new UnsupportedOperationException("Unimplemented method 'getState'");
    }
}

package edu.uj.po.simulation;

import edu.uj.po.simulation.interfaces.Pin;

public class ComponentPin implements Pin {
    private boolean value;
    @Override
    public boolean getPin() {
        return value;
    }
    @Override
    public void setPin(boolean value) {
        this.value = value;
    }
}

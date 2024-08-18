package edu.uj.po.simulation.gates;

import edu.uj.po.simulation.pins.GatePin;

public class NotGate extends BaseGate {
    public NotGate() {
        super();
        this.pins.put(0, new GatePin());
    }

    @Override
    public boolean getState() {
        return !pins.entrySet()
            .iterator()
            .next()
            .getValue()
            .getPin();
    }
}

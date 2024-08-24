package edu.uj.po.simulation.models.gates;

import edu.uj.po.simulation.pins.GatePin;

public class NotGate extends BaseGate {
    public NotGate() {
        super();
        this.pins.put(1, new GatePin());
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

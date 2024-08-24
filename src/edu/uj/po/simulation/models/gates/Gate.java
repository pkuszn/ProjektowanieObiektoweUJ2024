package edu.uj.po.simulation.models.gates;

import edu.uj.po.simulation.pins.GatePin;

public class Gate extends BaseGate {
    public Gate() {
        super();
        this.pins.put(1, new GatePin());
    }

    @Override
    public boolean getState() {
        return pins.entrySet()
            .iterator()
            .next()
            .getValue()
            .getPin();
    }
}

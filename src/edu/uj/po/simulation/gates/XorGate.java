package edu.uj.po.simulation.gates;

import edu.uj.po.simulation.pins.GatePin;

public class XorGate extends BaseGate {
    public XorGate(int size) {
        super();
        for (int i = 1; i < size + 1; i++) {
            this.pins.put(i, new GatePin());
        }
    }

    @Override
    public boolean getState() {
        long trueCount = pins.values().stream()
            .filter(GatePin::getPin)
            .count();

        return trueCount % 2 != 0;
    }
}

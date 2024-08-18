package edu.uj.po.simulation.gates;

import edu.uj.po.simulation.pins.GatePin;

public class NorGate extends BaseGate {
    public NorGate(int size) {
        super();
        for (int i = 1; i < size + 1; i++) {
            this.pins.put(i, new GatePin());
        }
    }

    @Override
    public boolean getState() {
        return pins.values()
            .stream()
            .noneMatch(GatePin::getPin);
    }
}

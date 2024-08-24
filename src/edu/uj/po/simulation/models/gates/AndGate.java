package edu.uj.po.simulation.models.gates;

import edu.uj.po.simulation.pins.GatePin;

public class AndGate extends BaseGate {
    public AndGate(int size) {
        for (int i = 1; i < size + 1; i++) {
            this.pins.put(i, new GatePin());
        }
    }
    
    @Override
    public boolean getState() {
        return pins.values()
            .stream()
            .allMatch(GatePin::getPin);
    }
}

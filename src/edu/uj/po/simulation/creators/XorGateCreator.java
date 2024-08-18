package edu.uj.po.simulation.creators;

import edu.uj.po.simulation.abstractions.LogicGate;
import edu.uj.po.simulation.abstractions.builders.LogicGateCreator;
import edu.uj.po.simulation.gates.XorGate;

public class XorGateCreator implements LogicGateCreator {

    public XorGateCreator() {
        super();
    }

    @Override
    public LogicGate createGate(int size) {
        return new XorGate(size);
    }
}

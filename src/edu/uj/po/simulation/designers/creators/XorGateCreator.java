package edu.uj.po.simulation.designers.creators;

import edu.uj.po.simulation.abstractions.LogicGate;
import edu.uj.po.simulation.abstractions.builders.LogicGateCreator;
import edu.uj.po.simulation.models.gates.XorGate;

public class XorGateCreator implements LogicGateCreator {

    public XorGateCreator() {
        super();
    }

    @Override
    public LogicGate createGate(int size) {
        return new XorGate(size);
    }
}
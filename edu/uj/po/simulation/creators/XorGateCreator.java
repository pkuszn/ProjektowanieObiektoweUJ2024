package edu.uj.po.simulation.creators;

import edu.uj.po.simulation.gates.XorGate;
import edu.uj.po.simulation.interfaces.LogicGate;
import edu.uj.po.simulation.interfaces.LogicGateCreator;

public class XorGateCreator implements LogicGateCreator {

    public XorGateCreator() {
        super();
    }

    @Override
    public LogicGate createGate(int size) {
        return new XorGate();
    }
}

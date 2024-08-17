package edu.uj.po.simulation.creators;

import edu.uj.po.simulation.gates.AndGate;
import edu.uj.po.simulation.interfaces.LogicGate;
import edu.uj.po.simulation.interfaces.builders.LogicGateCreator;

public class AndGateCreator implements LogicGateCreator {

    public AndGateCreator() {
        super();
    }

    @Override
    public LogicGate createGate(int size) {
        return new AndGate(size);
    }
}

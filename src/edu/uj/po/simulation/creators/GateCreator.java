package edu.uj.po.simulation.creators;

import edu.uj.po.simulation.abstractions.LogicGate;
import edu.uj.po.simulation.abstractions.builders.LogicGateCreator;
import edu.uj.po.simulation.gates.Gate;

public class GateCreator implements LogicGateCreator {

    @Override
    public LogicGate createGate(int size) {
        return new Gate();
    }
}

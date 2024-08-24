package edu.uj.po.simulation.designers.creators;

import edu.uj.po.simulation.abstractions.LogicGate;
import edu.uj.po.simulation.abstractions.builders.LogicGateCreator;
import edu.uj.po.simulation.models.gates.Gate;

public class GateCreator implements LogicGateCreator {

    @Override
    public LogicGate createGate(int size) {
        return new Gate();
    }
}

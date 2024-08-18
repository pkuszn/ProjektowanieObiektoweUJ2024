package edu.uj.po.simulation.creators;

import edu.uj.po.simulation.abstractions.LogicGate;
import edu.uj.po.simulation.abstractions.builders.LogicGateCreator;
import edu.uj.po.simulation.gates.NxorGate;

public class NxorGateCreator implements LogicGateCreator{

    public NxorGateCreator() {
        super();
    }

    @Override
    public LogicGate createGate(int size) {
        return new NxorGate();
    }
    
}

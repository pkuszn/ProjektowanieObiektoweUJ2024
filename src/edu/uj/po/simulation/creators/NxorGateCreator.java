package edu.uj.po.simulation.creators;

import edu.uj.po.simulation.gates.NxorGate;
import edu.uj.po.simulation.interfaces.LogicGate;
import edu.uj.po.simulation.interfaces.builders.LogicGateCreator;

public class NxorGateCreator implements LogicGateCreator{

    public NxorGateCreator() {
        super();
    }

    @Override
    public LogicGate createGate(int size) {
        return new NxorGate();
    }
    
}

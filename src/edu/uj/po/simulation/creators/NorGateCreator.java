package edu.uj.po.simulation.creators;

import edu.uj.po.simulation.abstractions.LogicGate;
import edu.uj.po.simulation.abstractions.builders.LogicGateCreator;
import edu.uj.po.simulation.gates.NorGate;

public class NorGateCreator implements LogicGateCreator {

    public NorGateCreator() {
        super();
    }

    @Override
    public LogicGate createGate(int size) {
        return new NorGate(size);
    }
    
}

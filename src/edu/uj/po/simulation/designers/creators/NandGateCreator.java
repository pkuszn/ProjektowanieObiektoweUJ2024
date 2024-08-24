package edu.uj.po.simulation.designers.creators;

import edu.uj.po.simulation.abstractions.LogicGate;
import edu.uj.po.simulation.abstractions.builders.LogicGateCreator;
import edu.uj.po.simulation.models.gates.NandGate;

public class NandGateCreator implements LogicGateCreator {

    public NandGateCreator() {
        super();
    }

    @Override
    public LogicGate createGate(int size) {
        return new NandGate(size);
    }
    
}

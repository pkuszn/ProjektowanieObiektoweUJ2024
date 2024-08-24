package edu.uj.po.simulation.designers.creators;

import edu.uj.po.simulation.abstractions.LogicGate;
import edu.uj.po.simulation.abstractions.builders.LogicGateCreator;
import edu.uj.po.simulation.models.gates.NorGate;

public class NorGateCreator implements LogicGateCreator {

    public NorGateCreator() {
        super();
    }

    @Override
    public LogicGate createGate(int size) {
        return new NorGate(size);
    }
    
}

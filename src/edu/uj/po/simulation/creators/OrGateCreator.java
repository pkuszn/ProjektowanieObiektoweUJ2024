package edu.uj.po.simulation.creators;

import edu.uj.po.simulation.abstractions.LogicGate;
import edu.uj.po.simulation.abstractions.builders.LogicGateCreator;
import edu.uj.po.simulation.gates.OrGate;

public class OrGateCreator implements LogicGateCreator {

    public OrGateCreator() {
        super();
    }

    @Override
    public LogicGate createGate(int size) {
        return new OrGate();
    }
    
}

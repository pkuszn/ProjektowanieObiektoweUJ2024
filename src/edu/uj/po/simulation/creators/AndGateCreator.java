package edu.uj.po.simulation.creators;

import edu.uj.po.simulation.abstractions.LogicGate;
import edu.uj.po.simulation.abstractions.builders.LogicGateCreator;
import edu.uj.po.simulation.gates.AndGate;


public class AndGateCreator implements LogicGateCreator {

    public AndGateCreator() {
        super();
    }

    
    @Override
    public LogicGate createGate(int size) {
        return new AndGate(size);
    }
}

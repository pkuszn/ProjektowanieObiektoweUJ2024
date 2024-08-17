package edu.uj.po.simulation.creators;

import edu.uj.po.simulation.gates.OrGate;
import edu.uj.po.simulation.interfaces.LogicGate;
import edu.uj.po.simulation.interfaces.builders.LogicGateCreator;

public class OrGateCreator implements LogicGateCreator {

    public OrGateCreator() {
        super();
    }

    @Override
    public LogicGate createGate(int size) {
        return new OrGate();
    }
    
}

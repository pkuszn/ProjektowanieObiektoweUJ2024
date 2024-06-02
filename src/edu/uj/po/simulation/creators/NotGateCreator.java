package edu.uj.po.simulation.creators;

import edu.uj.po.simulation.gates.NotGate;
import edu.uj.po.simulation.interfaces.LogicGate;
import edu.uj.po.simulation.interfaces.LogicGateCreator;

public class NotGateCreator implements LogicGateCreator {

    public NotGateCreator() {
        super();
    }

    @Override
    public LogicGate createGate(int size) {
        return new NotGate();
    }
    
}

package edu.uj.po.simulation.designers.creators;

import edu.uj.po.simulation.abstractions.LogicGate;
import edu.uj.po.simulation.abstractions.builders.LogicGateCreator;
import edu.uj.po.simulation.models.gates.NotGate;

public class NotGateCreator implements LogicGateCreator {

    public NotGateCreator() {
        super();
    }

    @Override
    public LogicGate createGate(int size) {
        return new NotGate();
    }
    
}

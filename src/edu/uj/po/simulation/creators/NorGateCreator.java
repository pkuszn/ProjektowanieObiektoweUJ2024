package edu.uj.po.simulation.creators;

import edu.uj.po.simulation.gates.NorGate;
import edu.uj.po.simulation.interfaces.LogicGate;
import edu.uj.po.simulation.interfaces.LogicGateCreator;

public class NorGateCreator implements LogicGateCreator {

    public NorGateCreator() {
        super();
    }

    @Override
    public LogicGate createGate(int size) {
        return new NorGate();
    }
    
}

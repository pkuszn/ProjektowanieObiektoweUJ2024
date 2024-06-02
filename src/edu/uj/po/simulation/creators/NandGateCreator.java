package edu.uj.po.simulation.creators;

import edu.uj.po.simulation.gates.NandGate;
import edu.uj.po.simulation.interfaces.LogicGate;
import edu.uj.po.simulation.interfaces.LogicGateCreator;

public class NandGateCreator implements LogicGateCreator {

    public NandGateCreator() {
        super();
    }

    @Override
    public LogicGate createGate(int size) {
        return new NandGate();
    }
    
}

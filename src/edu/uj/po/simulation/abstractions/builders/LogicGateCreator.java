package edu.uj.po.simulation.abstractions.builders;

import edu.uj.po.simulation.abstractions.LogicGate;

public interface LogicGateCreator {
    LogicGate createGate(int size);    
}

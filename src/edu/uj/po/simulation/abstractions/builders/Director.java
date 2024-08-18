package edu.uj.po.simulation.abstractions.builders;

import edu.uj.po.simulation.abstractions.IntegratedCircuit;
import edu.uj.po.simulation.interfaces.UnknownPin;

public interface Director {
    IntegratedCircuit make(IntegratedCircuitBuilder builder) throws UnknownPin;
}

package edu.uj.po.simulation.interfaces.builders;

import edu.uj.po.simulation.interfaces.IntegratedCircuit;
import edu.uj.po.simulation.interfaces.UnknownPin;

public interface Director {
    IntegratedCircuit make(IntegratedCircuitBuilder builder) throws UnknownPin;
}

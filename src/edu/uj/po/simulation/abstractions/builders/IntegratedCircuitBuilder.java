package edu.uj.po.simulation.abstractions.builders;

import edu.uj.po.simulation.abstractions.IntegratedCircuit;
import edu.uj.po.simulation.interfaces.UnknownPin;

public interface IntegratedCircuitBuilder {
    public void initCircuit();
    public void setLogicGates();
    public void connectPins() throws UnknownPin;
    public IntegratedCircuit getResult();
}

package edu.uj.po.simulation.interfaces.builders;

import edu.uj.po.simulation.interfaces.IntegratedCircuit;
import edu.uj.po.simulation.interfaces.UnknownPin;

public interface IntegratedCircuitBuilder {
    public void initCircuit();
    public void setLogicGates();
    public void connectPins() throws UnknownPin;
    public IntegratedCircuit getResult();
}

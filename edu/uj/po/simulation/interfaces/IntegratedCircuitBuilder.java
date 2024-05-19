package edu.uj.po.simulation.interfaces;

public interface IntegratedCircuitBuilder {
    public void initCircuit();
    public void setLogicGates();
    public void connectPins();
    public IntegratedCircuit getResult();
}

package edu.uj.po.simulation.interfaces;

public interface IntegratedCircuitBuilder {
    public IntegratedCircuit circuit = null;
    public void setLogicGates();
    public void connectGates();
    public void setInputs();
    public void setOutputs();
    public IntegratedCircuit getResult();
}

package edu.uj.po.simulation.interfaces;

public interface Director {
    IntegratedCircuit make(IntegratedCircuitBuilder builder) throws UnknownPin;
}

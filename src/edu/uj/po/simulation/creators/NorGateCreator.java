package edu.uj.po.simulation.creators;

public class NorGateCreator implements LogicGateCreator {

    public NorGateCreator() {
        super();
    }

    @Override
    public LogicGate createGate(int size) {
        return new NorGate();
    }
    
}

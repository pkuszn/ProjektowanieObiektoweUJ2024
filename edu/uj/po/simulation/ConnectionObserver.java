package edu.uj.po.simulation;

import edu.uj.po.simulation.interfaces.CircuitObserver;
import edu.uj.po.simulation.interfaces.IntegratedCircuit;

public class ConnectionObserver implements CircuitObserver {
    private IntegratedCircuit sourceCircuit;
    private IntegratedCircuit targetCircuit;
    private int sourcePin;
    private int targetPin;

    public ConnectionObserver() {
        super();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}

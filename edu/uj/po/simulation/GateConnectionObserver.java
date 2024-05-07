package edu.uj.po.simulation;

import edu.uj.po.simulation.interfaces.GateObserver;

public class GateConnectionObserver implements GateObserver {

    @Override
    public void update(boolean newState) {
        System.out.println("The state of the object is equals to -> " + newState);
    }
    
}

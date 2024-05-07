package edu.uj.po.simulation;

import edu.uj.po.simulation.creators.*;
import edu.uj.po.simulation.interfaces.*;

public class Main {
    public static void main(String[] args) {
        AndGateCreator andGateCreator = new AndGateCreator();
        LogicGate andGate = andGateCreator.createGate(2);
        GateObserver gateObserver = new GateConnectionObserver();
        andGate.addObserver(gateObserver);

        int ticks = 60;
        for (int i = 0; i<ticks; i++) {
            System.out.println("tick " + i);
            andGate.setPinState(1, true);
            andGate.setPinState(2, true);
        }
    }
}

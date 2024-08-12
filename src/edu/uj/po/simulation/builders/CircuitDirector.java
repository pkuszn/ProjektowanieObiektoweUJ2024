package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.interfaces.Director;
import edu.uj.po.simulation.interfaces.IntegratedCircuit;
import edu.uj.po.simulation.interfaces.IntegratedCircuitBuilder;
import edu.uj.po.simulation.interfaces.UnknownPin;

public class CircuitDirector implements Director {

    @Override
    public IntegratedCircuit make(IntegratedCircuitBuilder builder) throws UnknownPin {
        try {
            builder.initCircuit();
            builder.setLogicGates();
            builder.connectPins();
        } catch (Exception e) {
            System.out.println("Unknown error has occured on director side " + e.getMessage());
            throw e;
        }
        return builder.getResult();
    }
}
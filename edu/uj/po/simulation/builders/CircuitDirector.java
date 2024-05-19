package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.interfaces.Director;
import edu.uj.po.simulation.interfaces.IntegratedCircuit;
import edu.uj.po.simulation.interfaces.IntegratedCircuitBuilder;

public class CircuitDirector implements Director {

    @Override
    public IntegratedCircuit make(IntegratedCircuitBuilder builder) {
        builder.setLogicGates();
        builder.connectGates();
        builder.setInputs();
        builder.setOutputs();
        return builder.getResult();
    }
}

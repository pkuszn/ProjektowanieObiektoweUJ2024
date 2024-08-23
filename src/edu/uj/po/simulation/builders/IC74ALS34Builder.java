package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.IntegratedCircuit;
import edu.uj.po.simulation.abstractions.builders.IntegratedCircuitBuilder;
import edu.uj.po.simulation.circuits.IC74ALS34;
import edu.uj.po.simulation.creators.GateCreator;
import edu.uj.po.simulation.gates.Gate;
import edu.uj.po.simulation.interfaces.UnknownPin;

public class IC74ALS34Builder extends Builder implements IntegratedCircuitBuilder  {
    private IC74ALS34 integratedCircuit;
    private Gate gateOne;
    private Gate gateTwo;
    private Gate gateThree;
    private Gate gateFour;
    private Gate gateFive;
    private Gate gateSix;
    /**
     * Pin instrunction for 74ALS34 component
     * is in [/housings/74ALS34.png]
     */
    public IC74ALS34Builder() {
        super();
    }
    

    @Override
    public void initCircuit() {
        integratedCircuit = new IC74ALS34();
    }

    @Override
    public void setLogicGates() {
        GateCreator gateCreator = new GateCreator();
        gateOne = (Gate) gateCreator.createGate(1);
        gateTwo = (Gate) gateCreator.createGate(1);
        gateThree = (Gate) gateCreator.createGate(1);
        gateFour = (Gate) gateCreator.createGate(1);
        gateFive = (Gate) gateCreator.createGate(1);
        gateSix = (Gate) gateCreator.createGate(1);
    }

    @Override
    public void connectPins() throws UnknownPin {
        try {
            connectGateToOutput(gateOne, integratedCircuit, 2);
            connectGateToOutput(gateTwo, integratedCircuit, 4);

            connectGateToOutput(gateThree, integratedCircuit, 6);
            connectGateToOutput(gateFive, integratedCircuit, 8);

            connectGateToOutput(gateFive, integratedCircuit, 10);
            connectGateToOutput(gateSix, integratedCircuit, 12);

            connectInputToGate(integratedCircuit, 1, gateOne, 1);
            connectInputToGate(integratedCircuit, 3, gateTwo, 1);

            connectInputToGate(integratedCircuit, 5, gateThree, 1);
            connectInputToGate(integratedCircuit, 9, gateFour, 1);

            connectInputToGate(integratedCircuit, 11, gateFive, 1);
            connectInputToGate(integratedCircuit, 13, gateSix, 1);
        } catch (UnknownPin e) {
            throw e;
        }
    }

    @Override
    public IntegratedCircuit getResult() {
        return integratedCircuit;
    }
}

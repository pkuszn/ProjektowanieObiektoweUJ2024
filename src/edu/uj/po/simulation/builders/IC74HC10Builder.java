package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.IntegratedCircuit;
import edu.uj.po.simulation.abstractions.builders.IntegratedCircuitBuilder;
import edu.uj.po.simulation.circuits.IC74HC10;
import edu.uj.po.simulation.creators.NandGateCreator;
import edu.uj.po.simulation.gates.NandGate;
import edu.uj.po.simulation.interfaces.UnknownPin;

public class IC74HC10Builder extends Builder implements IntegratedCircuitBuilder {
    private IC74HC10 integratedCircuit;
    private NandGate nandGateOne;
    private NandGate nandGateTwo;
    private NandGate nandGateThree;

    /**
     * Pin instrunction for 74HC10 component
     * is in [/housings/74HC10.png]
     */
    public IC74HC10Builder() {
        super();
    }

    @Override
    public void initCircuit() {
        integratedCircuit = new IC74HC10();
    }

    @Override
    public void setLogicGates() {
        NandGateCreator nandGateCreator = new NandGateCreator();
        nandGateOne = (NandGate) nandGateCreator.createGate(3);
        nandGateTwo = (NandGate) nandGateCreator.createGate(3);
        nandGateThree = (NandGate) nandGateCreator.createGate(3);
    }

    @Override
    public void connectPins() throws UnknownPin {
        try {
            connectGateToOutput(nandGateOne, integratedCircuit, 12);
            connectGateToOutput(nandGateTwo, integratedCircuit, 6);
            connectGateToOutput(nandGateThree, integratedCircuit, 8);

            connectInputToGate(integratedCircuit, 13, nandGateOne, 1);
            connectInputToGate(integratedCircuit, 1, nandGateOne, 2);
            connectInputToGate(integratedCircuit, 2, nandGateOne, 3);

            connectInputToGate(integratedCircuit, 3, nandGateTwo, 1);
            connectInputToGate(integratedCircuit, 4, nandGateTwo, 2);
            connectInputToGate(integratedCircuit, 5, nandGateTwo, 3);

            connectInputToGate(integratedCircuit, 9, nandGateThree, 1);
            connectInputToGate(integratedCircuit, 10, nandGateThree, 2);
            connectInputToGate(integratedCircuit, 11, nandGateThree, 3);
        } catch (UnknownPin e) {
            throw e;
        }
    }

    @Override
    public IntegratedCircuit getResult() {
        return integratedCircuit;
    }
}

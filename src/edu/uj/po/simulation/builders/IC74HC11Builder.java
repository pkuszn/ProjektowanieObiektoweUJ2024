package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.IntegratedCircuit;
import edu.uj.po.simulation.abstractions.builders.IntegratedCircuitBuilder;
import edu.uj.po.simulation.circuits.IC74HC11;
import edu.uj.po.simulation.creators.AndGateCreator;
import edu.uj.po.simulation.gates.AndGate;
import edu.uj.po.simulation.interfaces.UnknownPin;

public class IC74HC11Builder extends Builder implements IntegratedCircuitBuilder {
    private IC74HC11 integratedCircuit;
    private AndGate andGateOne;
    private AndGate andGateTwo;
    private AndGate andGateThree;

    /**
     * Pin instrunction for 74HC11 component
     * is in [/housings/74HC11.png]
     */
    public IC74HC11Builder() {
        super();
    }

    @Override
    public void initCircuit() {
        integratedCircuit = new IC74HC11();
    }

    @Override
    public void setLogicGates() {
        AndGateCreator andGateCreator = new AndGateCreator();
        andGateOne = (AndGate) andGateCreator.createGate(3);
        andGateTwo = (AndGate) andGateCreator.createGate(3);
        andGateThree = (AndGate) andGateCreator.createGate(3);
    }

    @Override
    public void connectPins() throws UnknownPin {
        try {
            connectGateToOutput(andGateOne, integratedCircuit, 12);
            connectGateToOutput(andGateTwo, integratedCircuit, 6);
            connectGateToOutput(andGateThree, integratedCircuit, 8);

            connectInputToGate(integratedCircuit, 13, andGateOne, 1);
            connectInputToGate(integratedCircuit, 1, andGateOne, 2);
            connectInputToGate(integratedCircuit, 2, andGateOne, 3);

            connectInputToGate(integratedCircuit, 3, andGateTwo, 1);
            connectInputToGate(integratedCircuit, 4, andGateTwo, 2);
            connectInputToGate(integratedCircuit, 5, andGateTwo, 3);

            connectInputToGate(integratedCircuit, 9, andGateThree, 1);
            connectInputToGate(integratedCircuit, 10, andGateThree, 2);
            connectInputToGate(integratedCircuit, 11, andGateThree, 3);
        } catch (UnknownPin e) {
            throw e;
        }
    }

    @Override
    public IntegratedCircuit getResult() {
        return integratedCircuit;
    }
}

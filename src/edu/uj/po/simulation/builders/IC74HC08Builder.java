package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.IntegratedCircuit;
import edu.uj.po.simulation.abstractions.builders.IntegratedCircuitBuilder;
import edu.uj.po.simulation.circuits.IC74HC08;
import edu.uj.po.simulation.creators.AndGateCreator;
import edu.uj.po.simulation.gates.AndGate;
import edu.uj.po.simulation.interfaces.UnknownPin;

public class IC74HC08Builder extends Builder implements IntegratedCircuitBuilder {
    private IC74HC08 integratedCircuit;
    private AndGate andGateOne;
    private AndGate andGateTwo;
    private AndGate andGateThree;
    private AndGate andGateFour;

    /**
     * Pin instrunction for 74HC08 component
     * is in [/housings/74HC08.png]
     */
    public IC74HC08Builder() {
        super();
    }

    @Override
    public void initCircuit() {
        integratedCircuit = new IC74HC08();
    }

    @Override
    public void setLogicGates() {
        AndGateCreator andGateCreator = new AndGateCreator();
        andGateOne = (AndGate) andGateCreator.createGate(2);
        andGateTwo = (AndGate) andGateCreator.createGate(2);
        andGateThree = (AndGate) andGateCreator.createGate(2);
        andGateFour = (AndGate) andGateCreator.createGate(2);
    }

    @Override
    public void connectPins() throws UnknownPin {
        try {
            connectGateToOutput(andGateOne, integratedCircuit, 3);
            connectGateToOutput(andGateTwo, integratedCircuit, 6);
            connectGateToOutput(andGateThree, integratedCircuit, 8);
            connectGateToOutput(andGateFour, integratedCircuit, 11);

            connectInputToGate(integratedCircuit, 1, andGateOne, 1);
            connectInputToGate(integratedCircuit, 2, andGateOne, 2);

            connectInputToGate(integratedCircuit, 4, andGateTwo, 1);
            connectInputToGate(integratedCircuit, 5, andGateTwo, 2);

            connectInputToGate(integratedCircuit, 9, andGateThree, 1);
            connectInputToGate(integratedCircuit, 10, andGateThree, 2);

            connectInputToGate(integratedCircuit, 12, andGateFour, 1);
            connectInputToGate(integratedCircuit, 13, andGateFour, 2);
        } catch (UnknownPin e) {
            throw e;
        }
    }

    @Override
    public IntegratedCircuit getResult() {
        return integratedCircuit;
    }
}

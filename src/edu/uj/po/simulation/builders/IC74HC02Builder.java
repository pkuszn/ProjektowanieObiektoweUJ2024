package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.IntegratedCircuit;
import edu.uj.po.simulation.abstractions.builders.IntegratedCircuitBuilder;
import edu.uj.po.simulation.circuits.IC74HC02;
import edu.uj.po.simulation.creators.NorGateCreator;
import edu.uj.po.simulation.gates.NorGate;
import edu.uj.po.simulation.interfaces.UnknownPin;

public class IC74HC02Builder extends Builder implements IntegratedCircuitBuilder {
    private IC74HC02 integratedCircuit;
    private NorGate norGateOne;
    private NorGate norGateTwo;
    private NorGate norGateThree;
    private NorGate norGateFour;

    /**
     * Pin instrunction for 74HC02 component
     * is in [/housings/74HC02.png]
     */
    public IC74HC02Builder() {
        super();
    }

    @Override
    public void initCircuit() {
        integratedCircuit = new IC74HC02();
    }

    @Override
    public void setLogicGates() {
        NorGateCreator norGateCreator = new NorGateCreator();
        norGateOne = (NorGate) norGateCreator.createGate(2);
        norGateTwo = (NorGate) norGateCreator.createGate(2);
        norGateThree = (NorGate) norGateCreator.createGate(2);
        norGateFour = (NorGate) norGateCreator.createGate(2);
    }

    @Override
    public void connectPins() throws UnknownPin {
        try {
            connectGateToOutput(norGateOne, integratedCircuit, 1);
            connectGateToOutput(norGateTwo, integratedCircuit, 4);
            connectGateToOutput(norGateThree, integratedCircuit, 10);
            connectGateToOutput(norGateFour, integratedCircuit, 13);

            connectInputToGate(integratedCircuit, 2, norGateOne, 1);
            connectInputToGate(integratedCircuit, 3, norGateOne, 2);

            connectInputToGate(integratedCircuit, 5, norGateTwo, 1);
            connectInputToGate(integratedCircuit, 6, norGateTwo, 2);

            connectInputToGate(integratedCircuit, 8, norGateThree, 1);
            connectInputToGate(integratedCircuit, 9, norGateThree, 2);

            connectInputToGate(integratedCircuit, 11, norGateFour, 1);
            connectInputToGate(integratedCircuit, 12, norGateFour, 2);
        } catch (UnknownPin e) {
            throw e;
        }
    }

    @Override
    public IntegratedCircuit getResult() {
        return integratedCircuit;
    }
}

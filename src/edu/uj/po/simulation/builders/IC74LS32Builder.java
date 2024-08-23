package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.IntegratedCircuit;
import edu.uj.po.simulation.abstractions.builders.IntegratedCircuitBuilder;
import edu.uj.po.simulation.circuits.IC74LS32;
import edu.uj.po.simulation.creators.OrGateCreator;
import edu.uj.po.simulation.gates.OrGate;
import edu.uj.po.simulation.interfaces.UnknownPin;

public class IC74LS32Builder extends Builder implements IntegratedCircuitBuilder{
    private IC74LS32 integratedCircuit;
    private OrGate orGateOne;
    private OrGate orGateTwo;
    private OrGate orGateThree;
    private OrGate orGateFour;
    /**
     * Pin instrunction for 74LS32 component
     * is in [/housings/74LS32.png]
     */
    public IC74LS32Builder() {
        super();
    }

  @Override
    public void initCircuit() {
        integratedCircuit = new IC74LS32();
    }

    @Override
    public void setLogicGates() {
        OrGateCreator orGateCreator = new OrGateCreator();
        orGateOne = (OrGate) orGateCreator.createGate(2);
        orGateTwo = (OrGate) orGateCreator.createGate(2);
        orGateThree = (OrGate) orGateCreator.createGate(2);
        orGateFour = (OrGate) orGateCreator.createGate(2);
    }

    @Override
    public void connectPins() throws UnknownPin {
        try {
            connectGateToOutput(orGateOne, integratedCircuit, 3);
            connectGateToOutput(orGateTwo, integratedCircuit, 6);
            connectGateToOutput(orGateThree, integratedCircuit, 8);
            connectGateToOutput(orGateFour, integratedCircuit, 11);

            connectInputToGate(integratedCircuit, 1, orGateOne, 1);
            connectInputToGate(integratedCircuit, 2, orGateOne, 2);

            connectInputToGate(integratedCircuit, 4, orGateTwo, 1);
            connectInputToGate(integratedCircuit, 5, orGateTwo, 2);

            connectInputToGate(integratedCircuit, 9, orGateThree, 1);
            connectInputToGate(integratedCircuit, 10, orGateThree, 2);

            connectInputToGate(integratedCircuit, 12, orGateFour, 1);
            connectInputToGate(integratedCircuit, 13, orGateFour, 2);
        } catch (UnknownPin e) {
            throw e;
        }
    }

    @Override
    public IntegratedCircuit getResult() {
        return integratedCircuit;
    }
}

package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.IntegratedCircuit;
import edu.uj.po.simulation.abstractions.builders.IntegratedCircuitBuilder;
import edu.uj.po.simulation.circuits.IC74LS00;
import edu.uj.po.simulation.creators.NandGateCreator;
import edu.uj.po.simulation.gates.NandGate;
import edu.uj.po.simulation.interfaces.UnknownPin;

public class IC74LS00Builder extends Builder implements IntegratedCircuitBuilder {
    private IC74LS00 integratedCircuit;
    private NandGate nandGateOne;
    private NandGate nandGateTwo;
    private NandGate nandGateThree;
    private NandGate nandGateFour;

    /**
     * Pin instrunction for 74LS00 component
     * is in [/housings/74LS00.png]
     */
    public IC74LS00Builder() {
        super();
    }

    @Override
    public void initCircuit() {
        integratedCircuit = new IC74LS00();
    }

    @Override
    public void setLogicGates() {
        NandGateCreator andGateCreator = new NandGateCreator();
        nandGateOne = (NandGate) andGateCreator.createGate(2);
        nandGateTwo = (NandGate) andGateCreator.createGate(2);
        nandGateThree = (NandGate) andGateCreator.createGate(2);
        nandGateFour = (NandGate) andGateCreator.createGate(2);
    }

    /**
     * VCC - 14
     * GND - 7
     */
    @Override
    public void connectPins() throws UnknownPin {
        try {
            connectGateToOutput(nandGateOne, integratedCircuit, 3);
            connectGateToOutput(nandGateTwo, integratedCircuit, 6);
            connectGateToOutput(nandGateThree, integratedCircuit, 8);
            connectGateToOutput(nandGateFour, integratedCircuit, 11);

            connectInputToGate(integratedCircuit, 1, nandGateOne, 1);
            connectInputToGate(integratedCircuit, 2, nandGateOne, 2);

            connectInputToGate(integratedCircuit, 4, nandGateTwo, 1);
            connectInputToGate(integratedCircuit, 5, nandGateTwo, 2);

            connectInputToGate(integratedCircuit, 9, nandGateThree, 1);
            connectInputToGate(integratedCircuit, 10, nandGateThree, 2);

            connectInputToGate(integratedCircuit, 12, nandGateFour, 1);
            connectInputToGate(integratedCircuit, 13, nandGateFour, 2);
        } catch (UnknownPin e) {
            throw e;
        }
    }

    @Override
    public IntegratedCircuit getResult() {
        return integratedCircuit;
    }
}

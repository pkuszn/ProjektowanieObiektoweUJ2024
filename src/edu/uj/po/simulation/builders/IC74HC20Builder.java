package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.IntegratedCircuit;
import edu.uj.po.simulation.abstractions.builders.IntegratedCircuitBuilder;
import edu.uj.po.simulation.circuits.IC74HC20;
import edu.uj.po.simulation.creators.NandGateCreator;
import edu.uj.po.simulation.gates.NandGate;
import edu.uj.po.simulation.interfaces.UnknownPin;

public class IC74HC20Builder extends Builder implements IntegratedCircuitBuilder {
    private IC74HC20 integratedCircuit;
    private NandGate nandGateOne;
    private NandGate nandGateTwo;

    /**
     * Pin instrunction for 74HC20 component
     * is in [/housings/74HC20.png]
     */
    public IC74HC20Builder() {
        super();
    }

    @Override
    public void initCircuit() {
        integratedCircuit = new IC74HC20();
    }

    @Override
    public void setLogicGates() {
        NandGateCreator nandGateCreator = new NandGateCreator();
        nandGateOne = (NandGate) nandGateCreator.createGate(4);
        nandGateTwo = (NandGate) nandGateCreator.createGate(4);
    }
    /**
    * VCC - 14
    * NC - 3
    * NC - 11
    * GND - 7 
    */
    @Override
    public void connectPins() throws UnknownPin {
        try {
            connectGateToOutput(nandGateOne, integratedCircuit, 6);
            connectGateToOutput(nandGateTwo, integratedCircuit, 8);

            connectInputToGate(integratedCircuit, 1, nandGateOne, 1);
            connectInputToGate(integratedCircuit, 2, nandGateOne, 2);
            connectInputToGate(integratedCircuit, 4, nandGateOne, 3);
            connectInputToGate(integratedCircuit, 5, nandGateOne, 4);

            connectInputToGate(integratedCircuit, 9, nandGateTwo, 1);
            connectInputToGate(integratedCircuit, 10, nandGateTwo, 2);
            connectInputToGate(integratedCircuit, 12, nandGateTwo, 3);
            connectInputToGate(integratedCircuit, 13, nandGateTwo, 4);
        } catch (UnknownPin e) {
            throw e;
        }
    }

    @Override
    public IntegratedCircuit getResult() {
        return integratedCircuit;
    }
}

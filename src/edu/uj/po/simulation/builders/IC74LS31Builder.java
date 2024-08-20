package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.IntegratedCircuit;
import edu.uj.po.simulation.abstractions.builders.IntegratedCircuitBuilder;
import edu.uj.po.simulation.circuits.IC74LS31;
import edu.uj.po.simulation.creators.GateCreator;
import edu.uj.po.simulation.creators.NandGateCreator;
import edu.uj.po.simulation.creators.NotGateCreator;
import edu.uj.po.simulation.gates.Gate;
import edu.uj.po.simulation.gates.NandGate;
import edu.uj.po.simulation.gates.NotGate;
import edu.uj.po.simulation.interfaces.UnknownPin;

public class IC74LS31Builder extends Builder implements IntegratedCircuitBuilder {
    private IC74LS31 integratedCircuit;
    private NandGate nandGateOne;
    private NandGate nandGateTwo;
    private Gate gateOne;
    private Gate gateTwo;
    private NotGate notGateOne;
    private NotGate notGateTwo;

    /**
     * Pin instrunction for 74LS31 component
     * is in [/housings/74LS31.png]
     */
    public IC74LS31Builder() {
        super();
    }

    @Override
    public void initCircuit() {
        integratedCircuit = new IC74LS31();
    }

    @Override
    public void setLogicGates() {
        NandGateCreator andGateCreator = new NandGateCreator();
        GateCreator gateCreator = new GateCreator();
        NotGateCreator notGateCreator = new NotGateCreator();
        nandGateOne = (NandGate) andGateCreator.createGate(2);
        nandGateTwo = (NandGate) andGateCreator.createGate(2);
        gateOne = (Gate) gateCreator.createGate(1);
        gateTwo = (Gate) gateCreator.createGate(1);
        notGateOne = (NotGate) notGateCreator.createGate(1);
        notGateTwo = (NotGate) notGateCreator.createGate(1);
    }

    /**
     * VCC - 16
     * GND - 8
     */
    @Override
    public void connectPins() throws UnknownPin {
        try {
            connectGateToOutput(nandGateOne, integratedCircuit, 3);
            connectGateToOutput(nandGateTwo, integratedCircuit, 6);
            connectGateToOutput(gateOne, integratedCircuit, 8);
            connectGateToOutput(gateTwo, integratedCircuit, 11);
            connectGateToOutput(notGateTwo, integratedCircuit, 11);
            connectGateToOutput(notGateTwo, integratedCircuit, 11);

            connectInputToGate(integratedCircuit, 1, notGateOne, 1);

            connectInputToGate(integratedCircuit, 3, gateOne, 1);

            connectInputToGate(integratedCircuit, 5, nandGateOne, 1);
            connectInputToGate(integratedCircuit, 6, nandGateOne, 2);

            connectInputToGate(integratedCircuit, 10, nandGateOne, 1);
            connectInputToGate(integratedCircuit, 11, nandGateOne, 2);

            connectInputToGate(integratedCircuit, 13, notGateOne, 1);

            connectInputToGate(integratedCircuit, 15, gateOne, 1);
        } catch (UnknownPin e) {
            throw e;
        }
    }

    @Override
    public IntegratedCircuit getResult() {
        return integratedCircuit;
    }
}

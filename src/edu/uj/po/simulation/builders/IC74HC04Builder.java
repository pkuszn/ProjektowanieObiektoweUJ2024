package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.IntegratedCircuit;
import edu.uj.po.simulation.abstractions.builders.IntegratedCircuitBuilder;
import edu.uj.po.simulation.circuits.IC74HC04;
import edu.uj.po.simulation.creators.NotGateCreator;
import edu.uj.po.simulation.gates.NotGate;
import edu.uj.po.simulation.interfaces.UnknownPin;

public class IC74HC04Builder extends Builder implements IntegratedCircuitBuilder {
    private IC74HC04 integratedCircuit;
    private NotGate notGateOne;
    private NotGate notGateTwo;
    private NotGate notGateThree;
    private NotGate notGateFour;
    private NotGate notGateFive;
    private NotGate notGateSix;

    /**
     * Pin instrunction for 74HC04 component
     * is in [/housings/74HC04.png]
     */
    public IC74HC04Builder() {
        super();
    }

    @Override
    public void initCircuit() {
        integratedCircuit = new IC74HC04();
    }

    @Override
    public void setLogicGates() {
        NotGateCreator notGateCreator = new NotGateCreator();
        notGateOne = (NotGate) notGateCreator.createGate(1);
        notGateTwo = (NotGate) notGateCreator.createGate(1);
        notGateThree = (NotGate) notGateCreator.createGate(1);
        notGateFour = (NotGate) notGateCreator.createGate(1);
        notGateFive = (NotGate) notGateCreator.createGate(1);
        notGateSix = (NotGate) notGateCreator.createGate(1);
    }

    @Override
    public void connectPins() throws UnknownPin {
        try {
            connectGateToOutput(notGateOne, integratedCircuit, 2);
            connectGateToOutput(notGateTwo, integratedCircuit, 4);

            connectGateToOutput(notGateThree, integratedCircuit, 6);
            connectGateToOutput(notGateFive, integratedCircuit, 8);

            connectGateToOutput(notGateFive, integratedCircuit, 10);
            connectGateToOutput(notGateSix, integratedCircuit, 12);

            connectInputToGate(integratedCircuit, 1, notGateOne, 1);
            connectInputToGate(integratedCircuit, 3, notGateTwo, 1);

            connectInputToGate(integratedCircuit, 5, notGateThree, 1);
            connectInputToGate(integratedCircuit, 9, notGateFour, 1);

            connectInputToGate(integratedCircuit, 11, notGateFive, 1);
            connectInputToGate(integratedCircuit, 13, notGateSix, 1);
        } catch (UnknownPin e) {
            throw e;
        }
    }

    @Override
    public IntegratedCircuit getResult() {
        return integratedCircuit;
    }
}

package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.ComponentPin;
import edu.uj.po.simulation.circuits.IC74HC08;
import edu.uj.po.simulation.creators.AndGateCreator;
import edu.uj.po.simulation.interfaces.ComponentPinObserver;
import edu.uj.po.simulation.interfaces.GateObserver;
import edu.uj.po.simulation.interfaces.IntegratedCircuit;
import edu.uj.po.simulation.interfaces.IntegratedCircuitBuilder;
import edu.uj.po.simulation.interfaces.LogicGate;

public class IC74HC08Builder implements IntegratedCircuitBuilder {
    private IC74HC08 integratedCircuit;

    private LogicGate andGateOne;
    private LogicGate andGateTwo;
    private LogicGate andGateThree;
    private LogicGate andGateFour;

    public IC74HC08Builder() {
        super();
        integratedCircuit = new IC74HC08();
    }

    @Override
    public void setLogicGates() {
        AndGateCreator andGateCreator = new AndGateCreator();
        andGateOne = andGateCreator.createGate(2);
        andGateTwo = andGateCreator.createGate(2);
        andGateThree = andGateCreator.createGate(2);
        andGateFour = andGateCreator.createGate(2);
    }

    @Override
    public void connectGates() {
        connectGateToOutput(andGateOne, 1);
        connectGateToOutput(andGateTwo, 2);
        connectGateToOutput(andGateThree, 3);
        connectGateToOutput(andGateFour, 4);

        connectInputToGate(1, andGateOne, 1);
        connectInputToGate(2, andGateOne, 2);

        connectInputToGate(3, andGateTwo, 1);
        connectInputToGate(4, andGateTwo, 2);

        connectInputToGate(5, andGateThree, 1);
        connectInputToGate(6, andGateThree, 2);

        connectInputToGate(7, andGateFour, 1);
        connectInputToGate(8, andGateFour, 2);
    }

    @Override
    public void setInputs() {
        return;
    }

    @Override
    public void setOutputs() {
        return;
    }

    @Override
    public IntegratedCircuit getResult() {
        return integratedCircuit;
    }

    private void connectGateToOutput(LogicGate gate, int outputPinNumber) {
        ComponentPin outputComponentPin = integratedCircuit.getOutput(outputPinNumber);
        gate.addObserver(new GateObserver() {
            @Override
            public void update(boolean newState) {
                outputComponentPin.setPin(newState);
            }
        });
    }

    private void connectInputToGate(int inputPinNumber, LogicGate gate, int gatePinNumber) {
        ComponentPin pin = integratedCircuit.getInput(inputPinNumber);
        pin.addObserver(new ComponentPinObserver() {
            @Override
            public void update(boolean newState) {
                gate.setPinState(gatePinNumber, newState);
            }
        });
    }
}

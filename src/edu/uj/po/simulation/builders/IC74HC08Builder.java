package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.circuits.IC74HC08;
import edu.uj.po.simulation.creators.AndGateCreator;
import edu.uj.po.simulation.interfaces.IntegratedCircuit;
import edu.uj.po.simulation.interfaces.LogicGate;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.interfaces.builders.IntegratedCircuitBuilder;
import edu.uj.po.simulation.interfaces.observers.ComponentPinObserver;
import edu.uj.po.simulation.interfaces.observers.GateObserver;
import edu.uj.po.simulation.pins.ComponentPin;

public class IC74HC08Builder implements IntegratedCircuitBuilder {
    private IC74HC08 integratedCircuit;

    private LogicGate andGateOne;
    private LogicGate andGateTwo;
    private LogicGate andGateThree;
    private LogicGate andGateFour;

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
        andGateOne = andGateCreator.createGate(2);
        andGateTwo = andGateCreator.createGate(2);
        andGateThree = andGateCreator.createGate(2);
        andGateFour = andGateCreator.createGate(2);
    }

    @Override
    public void connectPins() throws UnknownPin {
        try {
            connectGateToOutput(andGateOne, 3);
            connectGateToOutput(andGateTwo, 6);
            connectGateToOutput(andGateThree, 8);
            connectGateToOutput(andGateFour, 11);

            connectInputToGate(1, andGateOne, 1);
            connectInputToGate(2, andGateOne, 2);
    
            connectInputToGate(4, andGateTwo, 1);
            connectInputToGate(5, andGateTwo, 2);
            
            connectInputToGate(9, andGateThree, 1);
            connectInputToGate(10, andGateThree, 2);

            connectInputToGate(12, andGateFour, 1);
            connectInputToGate(13, andGateFour, 2);
    
        } catch (UnknownPin e) {
            throw e;
        }
    }

    @Override
    public IntegratedCircuit getResult() {
        return integratedCircuit;
    }

    private void connectGateToOutput(LogicGate gate, int outputPinNumber) throws UnknownPin {
        try {
            ComponentPin outputComponentPin = integratedCircuit.getOutputPin(outputPinNumber);
            gate.addObserver(new GateObserver() {
                @Override
                public void update(boolean newState) {
                    outputComponentPin.setPin(newState);
                }
            });
        } catch (UnknownPin p) {
            System.out.println(p.getStackTrace());
            throw p;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            throw e;
        }
    }

    private void connectInputToGate(int inputPinNumber, LogicGate gate, int gatePinNumber) throws UnknownPin {
        try {
            ComponentPin pin = integratedCircuit.getInputPin(inputPinNumber);
            pin.addObserver(new ComponentPinObserver() {
                @Override
                public void update(boolean newState) {
                    gate.setPinState(gatePinNumber, newState);
                }
            });
        } catch (UnknownPin p) {
            System.out.println(p.getMessage());
            throw p;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}

package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.IntegratedCircuit;
import edu.uj.po.simulation.abstractions.LogicGate;
import edu.uj.po.simulation.abstractions.builders.IntegratedCircuitBuilder;
import edu.uj.po.simulation.circuits.IC74HC08;
import edu.uj.po.simulation.creators.AndGateCreator;
import edu.uj.po.simulation.gates.AndGate;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.pins.ComponentPin;
import java.util.Arrays;

public class IC74HC08Builder implements IntegratedCircuitBuilder {
    private IC74HC08 integratedCircuit;

    private AndGate andGateOne;
    private AndGate andGateTwo;
    private AndGate andGateThree;
    private AndGate andGateFour;

    /**
     * Pin instrunction for 74HC08 component
     * [../housings/74HC08.png]
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
            gate.addObserver((boolean newState) -> {
                outputComponentPin.setPin(newState);
            });
        } catch (UnknownPin p) {
            System.out.println(Arrays.toString(p.getStackTrace()));
            throw p;
        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            throw e;
        }
    }

    private void connectInputToGate(int inputPinNumber, LogicGate gate, int gatePinNumber) throws UnknownPin {
        try {
            ComponentPin pin = integratedCircuit.getInputPin(inputPinNumber);
            pin.addObserver((boolean newState) -> {
                gate.setPinState(gatePinNumber, newState);
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

package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.IntegratedCircuit;
import edu.uj.po.simulation.abstractions.LogicGate;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.pins.ComponentPin;
import java.util.Arrays;

public abstract class Builder {
    protected void connectGateToOutput(LogicGate gate, IntegratedCircuit integratedCircuit, int outputPinNumber)
            throws UnknownPin {
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

    protected void connectInputToGate(IntegratedCircuit integratedCircuit, int inputPinNumber, LogicGate gate,
            int gatePinNumber) throws UnknownPin {
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

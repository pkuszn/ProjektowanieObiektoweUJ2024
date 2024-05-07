package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.ComponentPin;
import edu.uj.po.simulation.circuits.IC74HC08;
import edu.uj.po.simulation.creators.AndGateCreator;
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
        ComponentPin componentPin1 = integratedCircuit.getOutput(1);
        componentPin1.getPin().set
    }

    @Override
    public void setInputs() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setInputs'");
    }

    @Override
    public void setOutputs() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setOutputs'");
    }

    @Override
    public IntegratedCircuit getResult() {
        return integratedCircuit;
    }
}

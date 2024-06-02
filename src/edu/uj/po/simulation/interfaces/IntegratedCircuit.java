package edu.uj.po.simulation.interfaces;

import edu.uj.po.simulation.pins.ComponentPin;

public interface IntegratedCircuit extends Component {
    public boolean getPinState(int pinNumber) throws UnknownPin;
    public void setPinState(int pinNumber, boolean value) throws UnknownPin;
    public ComponentPin getOutputPin(int pinNumber) throws UnknownPin;
    public ComponentPin getInputPin(int pinNumber) throws UnknownPin;
}

package edu.uj.po.simulation.abstractions;

import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.pins.ComponentPin;

public interface IntegratedCircuit extends Component {
    public boolean getPinState(int pinNumber) throws UnknownPin;
    public ComponentPin getOutputPin(int pinNumber) throws UnknownPin;
    public ComponentPin getInputPin(int pinNumber) throws UnknownPin;
}

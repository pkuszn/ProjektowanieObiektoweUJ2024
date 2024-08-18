package edu.uj.po.simulation.abstractions;

import edu.uj.po.simulation.interfaces.UnknownPin;

public interface OutputPinHeader extends Component {
    public boolean getPinState(int pinNumber) throws UnknownPin;
}

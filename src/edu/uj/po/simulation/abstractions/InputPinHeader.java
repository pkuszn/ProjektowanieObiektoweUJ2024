package edu.uj.po.simulation.abstractions;

import edu.uj.po.simulation.interfaces.UnknownPin;

public interface InputPinHeader extends Component {
    public void setPinState(int pinNumber, boolean value) throws UnknownPin, InterruptedException;
}

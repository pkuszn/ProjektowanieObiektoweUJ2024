package edu.uj.po.simulation.interfaces;

public interface OutputPinHeader extends Component {
    public boolean getPinState(int pinNumber) throws UnknownPin;
}

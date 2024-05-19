package edu.uj.po.simulation.interfaces;

public interface InputPinHeader extends Component {
    public void setPinState(int pinNumber, boolean value) throws UnknownPin;
}

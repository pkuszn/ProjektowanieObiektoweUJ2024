package edu.uj.po.simulation.models;

import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.interfaces.PinState;

public class ComponentPin {
    private final int pinNumber;
    private PinState state;
    private PinType pinType;
    private ComponentPin connectedPin;

    public ComponentPin(int pinNumber, PinType pinType) {
        this.pinNumber = pinNumber;
        this.pinType = pinType;
    }

    public PinState getState() {
        return state;
    }

    public void setState(PinState state) {
        this.state = state;
        if (connectedPin != null) {
            connectedPin.setState(state);
        }
    }

    public PinType getPinType() {
        return pinType;
    }

    public void setPinType(PinType pinType) {
        this.pinType = pinType;
    }

    public int getPinNumber() {
        return pinNumber;
    }

    public ComponentPin getConnectedPin() {
        return connectedPin;
    }

    public void setConnectedPin(ComponentPin connectedPin) {
        this.connectedPin = connectedPin;
    }
}
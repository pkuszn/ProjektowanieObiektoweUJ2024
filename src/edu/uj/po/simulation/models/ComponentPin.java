package edu.uj.po.simulation.models;

import java.util.ArrayList;
import java.util.List;

import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.interfaces.PinState;

public class ComponentPin {
    private final int pinNumber;
    private final int componentId; // for debug purpose only
    private List<ComponentPin> connectedPins;
    private PinState state;
    private PinType pinType;

    public ComponentPin(int pinNumber, PinType pinType, int componentId) {
        this.pinNumber = pinNumber;
        this.pinType = pinType;
        this.state = PinState.UNKNOWN;
        this.componentId = componentId;
        this.connectedPins = new ArrayList<>();
    }

    public int getComponentId() {
        return componentId;
    }

    public PinState getState() {
        return state;
    }

    public void setState(PinState state) {
        if (this.state == state) {
            return; // No state change, exit early
        }
    
        this.state = state;
        
        // Update all connected pins
        for (ComponentPin connectedPin : connectedPins) {
            if (connectedPin != null) {
                connectedPin.setState(state); // Propagate state change to connected pins
            }
        }
    }

    public void connectToPin(ComponentPin pin) {
        if (pin != null && !connectedPins.contains(pin)) {
            connectedPins.add(pin);
            pin.connectToPin(this); // Ensure bidirectional connection
        }
    }

    public void disconnectFromPin(ComponentPin pin) {
        if (pin != null && connectedPins.contains(pin)) {
            connectedPins.remove(pin);
            pin.disconnectFromPin(this); // Ensure bidirectional disconnection
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

    public List<ComponentPin> getConnectedPins() {
        return connectedPins;
    }

    public void setConnectedPins(List<ComponentPin> connectedPins) {
        this.connectedPins = connectedPins;
    }

    public String toJson() {
        StringBuilder connectedPinsJson = new StringBuilder("[");
        for (ComponentPin connectedPin : connectedPins) {
            connectedPinsJson.append(connectedPin.getPinNumber()).append(",");
        }
        if (!connectedPins.isEmpty()) {
            connectedPinsJson.setLength(connectedPinsJson.length() - 1); // Remove trailing comma
        }
        connectedPinsJson.append("]");
        
        return String.format(
            "{\"pinNumber\":%d, \"pinType\":\"%s\", \"state\":\"%s\", \"componentId\":%d, \"connectedPins\":%s}",
            pinNumber,
            pinType.name(), // Assuming PinType has a name() method
            state.name(),   // Assuming PinState has a name() method
            componentId,
            connectedPinsJson.toString()
        );
    }
}
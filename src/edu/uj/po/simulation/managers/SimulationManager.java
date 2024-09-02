package edu.uj.po.simulation.managers;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.interfaces.UnknownStateException;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.utils.PinsToJson;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SimulationManager {
    private final Map<Integer, Component> components; // integer as global identifier

    public SimulationManager() {
        super();
        this.components = new HashMap<>();
    }

    public Map<Integer, Component> getComponents() {
        return this.components;
    }

    public void setComponent(int globalId, Component component) {
        this.components.put(globalId, component);
    }

    public void resetComponents() {
        this.components.clear();
    }

    public Map<Integer, Set<ComponentPinState>> simulation(Set<ComponentPinState> states0, int numTicks) throws UnknownStateException {
        Map<Integer, Set<ComponentPinState>> simulationResult = new HashMap<>();
        Set<ComponentPinState> currentStates = new HashSet<>(states0);
    
        for (int tick = 0; tick < numTicks; tick++) {
            for (ComponentPinState state : currentStates) {
                Component component = components.get(state.componentId());
                if (component != null) {
                    try {
                        component.applyCommand();
                        PinsToJson.saveToJson(component.getGlobalId(), component.getPins(), tick);
                        ComponentPin pin = component.getPin(state.pinId());
                        if (pin == null) {
                            ComponentPinState unknownState = new ComponentPinState(component.getGlobalId(),
                            state.pinId(), state.state());
                            throw new UnknownStateException(unknownState);  
                        }
                        if (pin.getState() == state.state()) {
                            continue;
                        }
                        component.setState(state);
                    } catch (UnknownPin e) {
                        ComponentPinState unknownState = new ComponentPinState(component.getGlobalId(),
                        state.pinId(), state.state());
                        throw new UnknownStateException(unknownState);
                    }
                }
            }
    
            Set<ComponentPinState> tickStates = new HashSet<>();
            for (Component component : components.values()) {
                tickStates.addAll(component.getPins().values().stream()
                        .map(pin -> new ComponentPinState(component.getGlobalId(), pin.getPinNumber(), pin.getState()))
                        .toList());
            }
            simulationResult.put(tick, tickStates);
    
            currentStates = tickStates;
        }
    
        return simulationResult;
    }

    public void stationaryState(Set<ComponentPinState> states) throws UnknownStateException {
        for (ComponentPinState state : states) {
            Component component = components.get(state.componentId());
            if (component == null) {
                throw new UnknownStateException(state);
            }

            try {
                component.setState(state);
            } catch (UnknownPin e) {
                throw new UnknownStateException(state);
            }
        }

        for (Component component : components.values()) {
            component.applyCommand();
            PinsToJson.saveToJson(component.getGlobalId(), component.getPins());
        }
        int limit = this.components.size() + 1;
        int i = 0;
        boolean isUnknownState = true;
        while (isUnknownState == true) {
            i++;
            isUnknownState = false;
            for (Component component : components.values()) {
                component.applyCommand();

                for (ComponentPin pin : new HashSet<>(component.getPins().values())) {
                    if (pin.getState() == PinState.UNKNOWN && pin.getPinType() != PinType.OUT) {
                        if (pin.getConnectedPins().isEmpty() && component.getComponentClass() != ComponentClass.OUT) {
                            continue;
                        }
                        boolean hasUnknownConnectedPin = pin.getConnectedPins().stream()
                                .anyMatch(connectedPin -> connectedPin.getState() == PinState.UNKNOWN);

                        if (hasUnknownConnectedPin && i > limit) {
                            ComponentPinState unknownState = new ComponentPinState(component.getGlobalId(),
                                    pin.getPinNumber(), pin.getState());
                            throw new UnknownStateException(unknownState);
                        } else if (hasUnknownConnectedPin) {
                            isUnknownState = true;
                        }
                    }
                }
            }
        }
    }
}

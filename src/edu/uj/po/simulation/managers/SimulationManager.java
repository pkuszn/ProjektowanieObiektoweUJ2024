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
import java.util.stream.Collectors;

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

    public Map<Integer, Set<ComponentPinState>> simulation(Set<ComponentPinState> states0, int numTicks)
            throws UnknownStateException {
        Map<Integer, Set<ComponentPinState>> simulationResult = new HashMap<>();
        Set<ComponentPinState> currentStates = new HashSet<>(states0);

        for (int tick = 0; tick <= numTicks; tick++) {
            System.out.println("STARTING PROCESSING THE TICK NUMBER " + tick);
            for (ComponentPinState state : currentStates) {
                Component component = components.get(state.componentId());
                if (component != null) {
                    try {
                        component.applyCommand();

                        ComponentPin pin = component.getPin(state.pinId());
                        if (pin == null) {
                            throw new UnknownStateException(
                                    new ComponentPinState(component.getGlobalId(), state.pinId(), state.state()));
                        }

                        if (pin.getState() != state.state()) {
                            component.setState(state);
                        }

                    } catch (UnknownPin e) {
                        throw new UnknownStateException(
                                new ComponentPinState(component.getGlobalId(), state.pinId(), state.state()));
                    }
                }
            }

            Set<ComponentPinState> tickStates = components.values().stream()
                    .flatMap(component -> component.getPins().values().stream()
                            .map(pin -> new ComponentPinState(component.getGlobalId(), pin.getPinNumber(),
                                    pin.getState())))
                    .collect(Collectors.toSet());

            simulationResult.put(tick, tickStates);
            currentStates = tickStates;


            for (Component component : components.values()) {
                PinsToJson.saveToJson(component.getGlobalId(), component.getPins(), tick);
            }
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

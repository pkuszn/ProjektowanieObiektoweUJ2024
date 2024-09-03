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
        Set<Integer> outIds = this.components.values().stream()
                .filter(component -> component.getComponentClass() == ComponentClass.OUT)
                .map(Component::getGlobalId)
                .collect(Collectors.toSet());

        for (int tick = 0; tick <= numTicks; tick++) {
            System.out.println("STARTING PROCESSING THE TICK NUMBER" + tick);
            Map<Integer, Set<ComponentPinState>> groupComponentPinStates = groupComponentPinStates(currentStates);
            for (Integer componentId : groupComponentPinStates.keySet()) {
                Component updateableComponent = components.get(componentId);
                if (updateableComponent == null) {
                    continue;
                }
                updateableComponent.applyCommand();

                Set<ComponentPinState> states = groupComponentPinStates.get(componentId);
                for (ComponentPinState state : states) {
                    try {
                        ComponentPin pin = updateableComponent.getPin(state.pinId());
                        if (updateableComponent.getGlobalId() == 3 && pin.getPinNumber() == 10) {
                            System.out.println("Debug");
                        }
                        if (pin == null) {
                            ComponentPinState unknownState = new ComponentPinState(updateableComponent.getGlobalId(),
                                    state.pinId(), state.state());
                            throw new UnknownStateException(unknownState);
                        }

                        if (pin.getState() == state.state()) {
                            continue;
                        }

                        // updateableComponent.setState(state);
                        System.out.println("Component: " + updateableComponent.getGlobalId() + " Pin:" + state.pinId()
                                + " zmienił stan na " + state.state());
                    } catch (UnknownPin e) {
                        ComponentPinState unknownState = new ComponentPinState(updateableComponent.getGlobalId(),
                                state.pinId(), state.state());
                        throw new UnknownStateException(unknownState);

                    }
                }
            }

            Set<ComponentPinState> tickStates = components.values().stream()
                    .flatMap(component -> component.getPins().values().stream()
                            .map(pin -> new ComponentPinState(component.getGlobalId(), pin.getPinNumber(),
                                    pin.getState())))
                    .collect(Collectors.toSet());

            Set<ComponentPinState> outs = new HashSet<>();
            for (ComponentPinState pinState : tickStates) {
                if (outIds.contains(pinState.componentId())) {
                    outs.add(pinState);
                }
            }

            simulationResult.put(tick, outs);
            currentStates = tickStates;

            for (Component componentTick : this.components.values()) {
                PinsToJson.saveToJson(componentTick.getGlobalId(), componentTick.getPins(), tick);
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

    private Map<Integer, Set<ComponentPinState>> groupComponentPinStates(Set<ComponentPinState> currentStates) {
        return currentStates.stream()
                .collect(Collectors.groupingBy(
                        ComponentPinState::componentId,
                        Collectors.toSet()));
    }
}

package edu.uj.po.simulation.managers;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentObserver;
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
import java.util.List;
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

        try {

            for (ComponentPinState state : currentStates) {
                Component component = components.get(state.componentId());
                ComponentPin pin = component.getPin(state.pinId());
                if (pin.getState() != state.state()) {
                    component.setState(state);
                }
            }

            Map<Integer, Set<ComponentPinState>> tickStates = getComponentPinStates(this.components);
            Map<Integer, Set<ComponentPinState>> previousTickStates = new HashMap<>();
            simulationResult.put(0, getOutputHeader(tickStates, outIds));

            for (int tick = 1; tick <= numTicks; tick++) {
                System.out.println("STARTING PROCESSING THE TICK NUMBER " + tick);

                this.components.values().forEach(Component::applyCommandTick);

                previousTickStates = tickStates;
                tickStates = getComponentPinStates(this.components);

                Set<ComponentPinState> toUpdate = getChangedStates(tickStates, previousTickStates);
                notifyAllObservers(toUpdate);

                simulationResult.put(tick, getOutputHeader(tickStates, outIds));

                for (Component component : components.values()) {
                    PinsToJson.saveToJson(component.getGlobalId(), component.getPins(), tick);
                }
            }
        } catch (Exception e) {
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

    private void notifyAllObservers(Set<ComponentPinState> states) {
        if (states.isEmpty()) {
            return;
        }
        for (ComponentPinState state : states) {
            Component component = components.get(state.componentId());
            List<ComponentObserver> observers = component.getObservers().get(state.pinId());
            for (ComponentObserver observer : observers) {
                observer.update(state);
            }
        }
    }

    private Set<ComponentPinState> getOutputHeader(Set<ComponentPinState> tickStates, Set<Integer> outsId) {
        Set<ComponentPinState> outs = new HashSet<>();
        for (ComponentPinState state : tickStates) {
            if (outsId.contains(state.componentId())) {
                outs.add(state);
            }
        }
        return outs;
    }

    public Set<ComponentPinState> getChangedStates(Map<Integer, Set<ComponentPinState>> previousStates,
            Map<Integer, Set<ComponentPinState>> currentStates) {
        Set<ComponentPinState> changedStates = new HashSet<>();

        for (Map.Entry<Integer, Set<ComponentPinState>> entry : currentStates.entrySet()) {
            Integer componentId = entry.getKey();
            Set<ComponentPinState> currentStatesSet = entry.getValue();
            Set<ComponentPinState> previousStatesSet = previousStates.get(componentId);

            if (previousStatesSet == null) {
                // If no previous states, all current states are considered changed
                changedStates.addAll(currentStatesSet);
            } else {
                // Check which states have changed
                for (ComponentPinState currentState : currentStatesSet) {
                    if (!previousStatesSet.contains(currentState)) {
                        changedStates.add(currentState);
                    }
                }
            }
        }

        return changedStates;
    }

    public Map<Integer, Set<ComponentPinState>> getComponentPinStates(Map<Integer, Component> components) {
        return components.values().stream()
                .flatMap(component -> component.getPins().values().stream()
                        .map(pin -> new ComponentPinState(component.getGlobalId(), pin.getPinNumber(), pin.getState())))
                .collect(Collectors.groupingBy(
                        ComponentPinState::componentId,
                        Collectors.toSet()));
    }

    private Set<ComponentPinState> getOutputHeader(Map<Integer, Set<ComponentPinState>> tickStates,
            Set<Integer> outsId) {
        Set<ComponentPinState> outs = new HashSet<>();

        for (Map.Entry<Integer, Set<ComponentPinState>> entry : tickStates.entrySet()) {
            Integer componentId = entry.getKey();
            Set<ComponentPinState> states = entry.getValue();

            if (outsId.contains(componentId)) {
                outs.addAll(states);
            }
        }

        return outs;
    }
}

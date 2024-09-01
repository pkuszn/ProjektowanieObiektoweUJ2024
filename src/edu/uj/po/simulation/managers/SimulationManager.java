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

    public Map<Integer, Set<ComponentPinState>> simulation(Set<ComponentPinState> states0, int ticks)
            throws UnknownStateException {
        stationaryState(states0);
        Map<Integer, Set<ComponentPinState>> simulationResults = new HashMap<>();
        for (int tick = 0; tick <= ticks; tick++) {
            Set<ComponentPinState> currentTickStates = simulateTick(states0);

            notifyObserversAtTick(currentTickStates, tick);

            simulationResults.put(tick, currentTickStates);

            states0 = currentTickStates;
        }

        return simulationResults;
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
            for (ComponentPin pin : new HashSet<>(component.getPins().values())) {
                if (pin.getState() == PinState.UNKNOWN && pin.getPinType() != PinType.OUT) {
                    boolean hasUnknownConnectedPin = pin.getConnectedPins().stream()
                            .anyMatch(connectedPin -> connectedPin.getState() == PinState.UNKNOWN);

                    if (hasUnknownConnectedPin) {
                        ComponentPinState unknownState = new ComponentPinState(component.getGlobalId(),
                                pin.getPinNumber(),
                                pin.getState());
                        throw new UnknownStateException(unknownState);
                    }
                }
            }

            if (component.getComponentClass() == ComponentClass.IC) {
                component.applyCommand();
            }

            PinsToJson.saveToJson(component.getGlobalId(), component.getPins());

            for (ComponentPin pin : new HashSet<>(component.getPins().values())) {
                if (pin.getState() == PinState.UNKNOWN) {
                    boolean hasUnknownConnectedPin = pin.getConnectedPins().stream()
                            .anyMatch(connectedPin -> connectedPin.getState() == PinState.UNKNOWN);

                    if (hasUnknownConnectedPin) {
                        ComponentPinState unknownState = new ComponentPinState(component.getGlobalId(),
                                pin.getPinNumber(),
                                pin.getState());
                        throw new UnknownStateException(unknownState);
                    }
                }
            }
        }
    }

    private void notifyObserversAtTick(Set<ComponentPinState> currentTickStates, int tick) {
        for (ComponentPinState state : currentTickStates) {
            Component component = components.get(state.componentId());

            for (ComponentObserver observer : component.getObservers()) {
                observer.update(state.state());
            }
        }
    }

    private Set<ComponentPinState> simulateTick(Set<ComponentPinState> states0) {
        Set<ComponentPinState> nextStates = new HashSet<>();
        for (ComponentPinState state : states0) {
            Component component = components.get(state.componentId());
            if (component != null) {
                if (component.getComponentClass() == ComponentClass.IC) {
                    component.applyCommand();
                }
                for (ComponentPin pin : new HashSet<>(component.getPins().values())) {
                    if (pin.getState() != state.state()) {
                        try {
                            component.setState(state);
                            nextStates.add(
                                    new ComponentPinState(component.getGlobalId(), pin.getPinNumber(), pin.getState()));
                        } catch (UnknownPin e) {
                        }
                    }
                }
            }
        }
        return nextStates;
    }
}

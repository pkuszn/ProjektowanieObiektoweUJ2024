package edu.uj.po.simulation;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentBuilder;
import edu.uj.po.simulation.abstractions.ComponentObserver;
import edu.uj.po.simulation.abstractions.Director;
import edu.uj.po.simulation.abstractions.HeaderBuilder;
import edu.uj.po.simulation.builders.ComponentDirector;
import edu.uj.po.simulation.builders.IC74HC08Builder;
import edu.uj.po.simulation.builders.InputHeaderBuilder;
import edu.uj.po.simulation.builders.OutputHeaderBuilder;
import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.ShortCircuitException;
import edu.uj.po.simulation.interfaces.UnknownChip;
import edu.uj.po.simulation.interfaces.UnknownComponent;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.interfaces.UnknownStateException;
import edu.uj.po.simulation.interfaces.UserInterface;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.headers.InputPinHeader;
import edu.uj.po.simulation.models.headers.OutputPinHeader;
import edu.uj.po.simulation.utils.ComponentLogger;
import edu.uj.po.simulation.utils.PinsToJson;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserInterfaceImpl implements UserInterface {
    private final Map<Integer, Component> components; // integer as global identifier
    private final Map<Integer, ComponentBuilder> builders; // integer as type of circuit
    private final Director director;

    public UserInterfaceImpl() {
        super();
        this.components = new HashMap<>();
        this.builders = new HashMap<>();
        this.director = new ComponentDirector();
        this.builders.put(7408, new IC74HC08Builder());
    }

    @Override
    public int createChip(int code) throws UnknownChip, UnknownPin {
        ComponentBuilder builder = builders.get(code);
        if (builder == null) {
            throw new UnknownChip();
        }
        Component component = director.make(builder);
        int globalId = component.getGlobalId();
        components.put(globalId, component);
        return globalId;
    }

    @Override
    public int createInputPinHeader(int size) {
        HeaderBuilder builder = new InputHeaderBuilder();
        InputPinHeader inputHeader = (InputPinHeader) director.make(builder, size);
        int globalId = inputHeader.getGlobalId();
        components.put(globalId, inputHeader);
        return globalId;
    }

    @Override
    public int createOutputPinHeader(int size) {
        HeaderBuilder builder = new OutputHeaderBuilder();
        OutputPinHeader outputPinHeader = (OutputPinHeader) director.make(builder, size);
        int globalId = outputPinHeader.getGlobalId();
        components.put(globalId, outputPinHeader);
        return globalId;
    }

    @Override
    public void connect(int component1, int pin1, int component2, int pin2)
            throws UnknownComponent, UnknownPin, ShortCircuitException {
        Component firstComponent = getComponent(component1);
        Component secondComponent = getComponent(component2);

        if (firstComponent == null) {
            throw new UnknownComponent(component1);
        }

        if (secondComponent == null) {
            throw new UnknownComponent(component2);
        }

        ComponentPin componentPin1 = firstComponent.getPin(pin1);
        ComponentPin componentPin2 = secondComponent.getPin(pin2);

        if (componentPin1 == null || componentPin1.getPinType() == PinType.NONE) {
            throw new UnknownPin(component1, pin1);
        }

        if (componentPin2 == null || componentPin2.getPinType() == PinType.NONE) {
            throw new UnknownPin(component2, pin2);
        }

        if (componentPin1.getPinType() == PinType.OUT && componentPin2.getPinType() == PinType.OUT
                && (firstComponent.getComponentClass() != ComponentClass.OUT
                        && secondComponent.getComponentClass() != ComponentClass.OUT)) {
            throw new ShortCircuitException();
        }

        componentPin1.connectToPin(componentPin2);
        addObserver(firstComponent, pin1, secondComponent, pin2);
    }

    private Component getComponent(int component) throws UnknownComponent {
        Component circuit = components.get(component);
        if (circuit == null) {
            throw new UnknownComponent(component);
        }
        return circuit;
    }

    private void addObserver(Component source, int sourcePin, Component target, int targetPin) throws UnknownPin {
        try {
            source.addObserver(value -> {
                ComponentPinState state = new ComponentPinState(target.getGlobalId(), targetPin, value);
                // target.setState(state);
                ComponentLogger.logAddObserver(source.getGlobalId(), sourcePin, target.getGlobalId(), targetPin);
            });
        } catch (UnknownPin e) {
            throw e;
        }
    }

    @Override
    public void stationaryState(Set<ComponentPinState> states) throws UnknownStateException {
        for (ComponentPinState state : states) {
            if (state.state() == PinState.UNKNOWN) {
                throw new UnknownStateException(state);
            }

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

    @Override
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

    @Override
    public Set<Integer> optimize(Set<ComponentPinState> states0, int ticks) throws UnknownStateException {
        return null;
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

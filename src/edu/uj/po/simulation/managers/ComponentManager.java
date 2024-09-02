package edu.uj.po.simulation.managers;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.Director;
import edu.uj.po.simulation.builders.ComponentDirector;
import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.interfaces.ShortCircuitException;
import edu.uj.po.simulation.interfaces.UnknownChip;
import edu.uj.po.simulation.interfaces.UnknownComponent;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.utils.ComponentLogger;
import java.util.HashMap;
import java.util.Map;

public class ComponentManager {
    private final Map<Integer, Component> components; // integer as global identifier
    private final Director director;
    private final SimulationManager manager;

    public ComponentManager(SimulationManager manager) {
        super();
        this.manager = manager;
        this.components = new HashMap<>();
        this.director = new ComponentDirector();
    }

    public int createChip(int code) throws UnknownChip, UnknownPin {
        Component component = director.orderComponentBuild(code);
        int globalId = component.getGlobalId();
        components.put(globalId, component);
        manager.setComponent(globalId, component);
        return globalId;
    }

    public int createInputPinHeader(int size) {
        Component inputHeader = director.orderHeaderBuild(ComponentClass.IN, size);
        int globalId = inputHeader.getGlobalId();
        components.put(globalId, inputHeader);
        manager.setComponent(globalId, inputHeader);
        return globalId;
    }

    public int createOutputPinHeader(int size) {
        Component outputPinHeader = director.orderHeaderBuild(ComponentClass.OUT, size);
        int globalId = outputPinHeader.getGlobalId();
        components.put(globalId, outputPinHeader);
        manager.setComponent(globalId, outputPinHeader);
        return globalId;
    }

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
                // ComponentPinState state = new ComponentPinState(target.getGlobalId(), targetPin, value);
                ComponentLogger.logPinState(source.getGlobalId(), sourcePin, value);
            });
        } catch (UnknownPin e) {
            throw e;
        }
    }
}

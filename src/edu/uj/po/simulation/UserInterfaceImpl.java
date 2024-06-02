package edu.uj.po.simulation;

import edu.uj.po.simulation.builders.CircuitDirector;
import edu.uj.po.simulation.builders.IC74HC08Builder;
import edu.uj.po.simulation.headers.InputPinHeaderImpl;
import edu.uj.po.simulation.headers.OutputPinHeaderImpl;
import edu.uj.po.simulation.interfaces.Component;
import edu.uj.po.simulation.interfaces.ComponentClass;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.IntegratedCircuit;
import edu.uj.po.simulation.interfaces.IntegratedCircuitBuilder;
import edu.uj.po.simulation.interfaces.PinType;
import edu.uj.po.simulation.interfaces.ShortCircuitException;
import edu.uj.po.simulation.interfaces.UnknownChip;
import edu.uj.po.simulation.interfaces.UnknownComponent;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.interfaces.UnknownStateException;
import edu.uj.po.simulation.interfaces.UserInterface;
import edu.uj.po.simulation.utils.PinStateMapper;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserInterfaceImpl implements UserInterface {

    private final Map<Integer, Component> components; // integer as global identifier
    private final Map<Integer, IntegratedCircuitBuilder> builders; // integer as type of circuit
    private final CircuitDirector director;

    public UserInterfaceImpl() {
        super();
        this.components = new HashMap<>();
        this.builders = new HashMap<>();
        this.director = new CircuitDirector();
        this.builders.put(7408, new IC74HC08Builder());
    }

    @Override
    public int createChip(int code) throws UnknownChip, UnknownPin {
        IntegratedCircuitBuilder builder = builders.get(code);
        if (builder == null) {
            throw new UnknownChip();
        }
        try {
            IntegratedCircuit circuit = director.make(builder);
            int globalId = circuit.getGlobalId();
            components.put(globalId, circuit);
            return globalId;
        } catch (UnknownPin e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public int createInputPinHeader(int size) {
        InputPinHeaderImpl inputHeader = new InputPinHeaderImpl(size);
        int globalId = inputHeader.getGlobalId();
        components.put(globalId, inputHeader);
        return globalId;
    }

    @Override
    public int createOutputPinHeader(int size) {
        OutputPinHeaderImpl outputHeader = new OutputPinHeaderImpl(size);
        int globalId = outputHeader.getGlobalId();
        components.put(globalId, outputHeader);
        return globalId;
    }

    @Override
    public void connect(int component1, int pin1, int component2, int pin2)
            throws UnknownComponent, UnknownPin, ShortCircuitException {
        Component firstComponent = getComponent(component1);
        Component secondComponent = getComponent(component2);

        PinType pinType1 = firstComponent.getPinType(pin1);
        PinType pinType2 = secondComponent.getPinType(pin2);

        if (pinType1 == PinType.OUT && pinType2 == PinType.OUT
                && (firstComponent.getComponentClass() != ComponentClass.OUT
                        && secondComponent.getComponentClass() != ComponentClass.OUT)) {
            throw new ShortCircuitException();
        }

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
            source.addObserver(sourcePin, value -> {
                ComponentPinState state = new ComponentPinState(target.getGlobalId(), targetPin,
                        PinStateMapper.toPinState(value));
                try {
                    target.setState(state);
                } catch (UnknownPin e) {
                    System.out.println(e.getMessage());
                }
                System.out.println(toMessage(source, sourcePin, target, targetPin));
            });
        } catch (UnknownPin e) {
            throw e;
        }
    }

    @Override
    public void stationaryState(Set<ComponentPinState> states) throws UnknownStateException {
        return;
    }

    @Override
    public Map<Integer, Set<ComponentPinState>> simulation(Set<ComponentPinState> states0, int ticks)
            throws UnknownStateException {
        for (ComponentPinState componentPinState : states0) {
            try {
                Component component = components.get(componentPinState.componentId());
                component.setState(componentPinState);
            } catch (UnknownPin e) {
                System.out.println(e.getMessage());
            }
        }

        Map<Integer, Set<ComponentPinState>> result = new HashMap<>();

        for (Map.Entry<Integer, Component> component : components.entrySet()) {
            result.put(component.getKey(), component.getValue().getStates());
        }

        return result;
    }

    @Override
    public Set<Integer> optimize(Set<ComponentPinState> states0, int ticks) throws UnknownStateException {
        return null;
    }

    private static String toMessage(Component source, int sourcePin, Component target, int targetPin) {
        if (source == null || target == null) {
            System.out.println("Can't connect because of nullable");
        }

        StringBuilder str = new StringBuilder("Connection: ");
        str.append("Component with id: ").append(source.getGlobalId()).append("\n");
        str.append("connected with component id: ").append(target.getGlobalId()).append("\n");
        str.append("[").append(source.getGlobalId()).append("] ").append(sourcePin).append("   ---->    [").append(target.getGlobalId()).append("] ").append(targetPin);
        return str.toString();
    }

}

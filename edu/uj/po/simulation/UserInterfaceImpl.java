package edu.uj.po.simulation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import edu.uj.po.simulation.builders.CircuitDirector;
import edu.uj.po.simulation.builders.IC74HC08Builder;
import edu.uj.po.simulation.headers.InputPinHeaderImpl;
import edu.uj.po.simulation.headers.OutputPinHeaderImpl;
import edu.uj.po.simulation.interfaces.Component;
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

public class UserInterfaceImpl implements UserInterface {

    private Map<Integer, Component> components; // integer as global identifier
    private Map<Integer, IntegratedCircuitBuilder> builders; // integer as type of circuit
    private CircuitDirector director;

    public UserInterfaceImpl() {
        super();
        this.components = new HashMap<>();
        this.builders = new HashMap<>();
        director = new CircuitDirector();

        this.builders.put(7408, new IC74HC08Builder());
    }

    @Override
    public int createChip(int code) throws UnknownChip {
        IntegratedCircuitBuilder builder = builders.get(code);
        if (builder == null) {
            throw new UnknownChip();
        }

        IntegratedCircuit circuit = director.make(builder);
        int globalId = circuit.getGlobalId();
        components.put(globalId, circuit);

        return globalId;
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
        OutputPinHeaderImpl outputHeader= new OutputPinHeaderImpl(size);
        int globalId = outputHeader.getGlobalId();
        components.put(globalId, outputHeader);
        return globalId;
    }

    @Override
    public void connect(int component1, int pin1, int component2, int pin2) throws UnknownComponent, UnknownPin, ShortCircuitException {
        Component integratedCircuit1 = getComponent(component1);
        Component integratedCircuit2 = getComponent(component2);
    
        PinType pinType1 = integratedCircuit1.getPinType(pin1);
        PinType pinType2 = integratedCircuit2.getPinType(pin2);
    
        if (pinType1 == PinType.OUT && pinType2 == PinType.OUT) {
            throw new ShortCircuitException();
        }
    
        if (pinType1 == PinType.OUT && pinType2 == PinType.IN) {
            addObserver(integratedCircuit1, pin1, integratedCircuit2, pin2);
        } else if (pinType1 == PinType.IN && pinType2 == PinType.OUT) {
            addObserver(integratedCircuit2, pin2, integratedCircuit1, pin1);
        } else {
            addObserver(integratedCircuit1, pin1, integratedCircuit2, pin2);
            addObserver(integratedCircuit2, pin2, integratedCircuit1, pin1);
        }
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
                ComponentPinState state = new ComponentPinState(target.getGlobalId(), targetPin, PinStateMapper.toPinState(value));
                target.setState(state);
            });
        } catch (UnknownPin e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stationaryState(Set<ComponentPinState> states) throws UnknownStateException {
        return;
    }

    @Override
    public Map<Integer, Set<ComponentPinState>> simulation(Set<ComponentPinState> states0, int ticks) throws UnknownStateException {
        for (ComponentPinState componentPinState : states0) {
            Component component = components.get(componentPinState.componentId());
            component.setState(componentPinState);
        }

        //simulation
        for(int i = 0; i <= ticks; i++) {
            System.out.println("tick: " + i + "\n");
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
    
}

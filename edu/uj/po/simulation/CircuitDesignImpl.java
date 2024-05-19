package edu.uj.po.simulation;

import java.util.HashMap;
import java.util.Map;

import edu.uj.po.simulation.builders.CircuitDirector;
import edu.uj.po.simulation.builders.IC74HC08Builder;
import edu.uj.po.simulation.headers.InputPinHeaderImpl;
import edu.uj.po.simulation.headers.OutputPinHeaderImpl;
import edu.uj.po.simulation.interfaces.CircuitDesign;
import edu.uj.po.simulation.interfaces.Component;
import edu.uj.po.simulation.interfaces.IntegratedCircuit;
import edu.uj.po.simulation.interfaces.IntegratedCircuitBuilder;
import edu.uj.po.simulation.interfaces.PinType;
import edu.uj.po.simulation.interfaces.ShortCircuitException;
import edu.uj.po.simulation.interfaces.UnknownChip;
import edu.uj.po.simulation.interfaces.UnknownComponent;
import edu.uj.po.simulation.interfaces.UnknownPin;

public class CircuitDesignImpl implements CircuitDesign {

    private Map<Integer, Component> components; // integer as global identifier
    private Map<Integer, IntegratedCircuitBuilder> builders; // integer as type of circuit
    private CircuitDirector director;

    public CircuitDesignImpl() {
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
        IntegratedCircuit integratedCircuit1 = getCircuit(component1);
        IntegratedCircuit integratedCircuit2 = getCircuit(component2);
    
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
    
    private IntegratedCircuit getCircuit(int component) throws UnknownComponent {
        IntegratedCircuit circuit = (IntegratedCircuit) components.get(component);
        if (circuit == null) {
            throw new UnknownComponent(component);
        }
        return circuit;
    }
    
    private void addObserver(IntegratedCircuit source, int sourcePin, IntegratedCircuit target, int targetPin) {
        try {
            source.addObserver(sourcePin, value -> {
                try {
                    target.setPinState(targetPin, value);
                } catch (UnknownPin e) {
                    e.printStackTrace();
                }
            });
        } catch (UnknownPin e) {
            e.printStackTrace();
        }
    }
}

package edu.uj.po.simulation.circuits;

import edu.uj.po.simulation.abstractions.IntegratedCircuit;
import edu.uj.po.simulation.abstractions.observers.ComponentObserver;
import edu.uj.po.simulation.enums.ComponentBehaviour;
import edu.uj.po.simulation.enums.ComponentClass;
import edu.uj.po.simulation.enums.PinType;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.pins.ComponentPin;
import edu.uj.po.simulation.recorder.ComponentState;
import edu.uj.po.simulation.recorder.ComponentStateRecorder;
import edu.uj.po.simulation.utils.ComponentLogger;
import edu.uj.po.simulation.utils.PinStateMapper;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseComponent implements IntegratedCircuit {
    protected static final AtomicInteger counter = new AtomicInteger();
    protected final int globalId;
    protected final ComponentStateRecorder recorder;
    protected String type;
    protected Map<Integer, ComponentPin> inputs;
    protected Map<Integer, ComponentPin> outputs;
    protected Map<Integer, List<ComponentObserver>> observers;
    protected ComponentClass componentClass;
    protected String humanName;
    protected ComponentBehaviour behaviour;
    protected int tick;

    public BaseComponent() {
        super();
        this.globalId = counter.incrementAndGet();
        this.recorder = ComponentStateRecorder.getInstance();
    }

    @Override
    public int getGlobalId() {
        return globalId;
    }

    protected void addComponentState(int id, ComponentState componentState) {
        recorder.addComponentState(id, componentState);
    }
    
    protected void addComponentStates(Map<Integer, ComponentState> componentStatesMap) {
        recorder.addComponentStates(componentStatesMap);
    }

    @Override
    public void setTick(int tick) {
        this.tick = tick;
    }

    @Override
    public String getHumanName() {
        return humanName;
    }

    @Override
    public ComponentClass getComponentClass() {
        return componentClass;
    }

    public Map<Integer, ComponentPin> getInputs() {
        return inputs;
    }

    public Map<Integer, ComponentPin> getOutputs() {
        return outputs;
    }

    public Map<Integer, List<ComponentObserver>> getObservers() {
        return observers;
    }

    public ComponentBehaviour getBehaviour() {
        return behaviour;
    }

    @Override
    public void setBehaviour(ComponentBehaviour behaviour) {
        this.behaviour = behaviour;
    }

    @Override
    public String getComponentType() {
        return type;
    }

    @Override
    public void setPinState(int pinNumber, boolean value) throws UnknownPin, InterruptedException {
        while (true) {
            if (getBehaviour() == ComponentBehaviour.UNLOCK) {
                System.out.println(this.globalId +  " unlock");
                ComponentPin componentPin = inputs.get(pinNumber);
                if (componentPin == null) {
                    componentPin = outputs.get(pinNumber);
                }
        
                if (componentPin == null) {
                    throw new UnknownPin(this.getGlobalId(), pinNumber);
                }
        
				ComponentState componentState = new ComponentState(
                    pinNumber, 
                    humanName, 
                    type, 
                    componentClass, 
                    pinNumber, 
                    tick, 
                    PinStateMapper.toPinState(value), 
                    LocalDateTime.now());
                this.addComponentState(this.globalId, componentState);
                componentPin.setPin(value);
                notifyObserver(pinNumber);
                break;
            }
        }
    }

    @Override
    public boolean getPinState(int pinNumber) throws UnknownPin {
        ComponentPin componentPin = outputs.get(pinNumber);
        if (componentPin == null) {
            componentPin = inputs.get(pinNumber);
        }

        if (componentPin == null) {
            throw new UnknownPin(this.getGlobalId(), pinNumber);
        }

        return componentPin.getPin();
    }

    @Override
    public PinType getPinType(int pinNumber) throws UnknownPin {
        ComponentPin pinIn = inputs.get(pinNumber);
        ComponentPin pinOut = outputs.get(pinNumber);
        if (pinIn != null) {
            return PinType.IN;
        } else if (pinOut != null) {
            return PinType.OUT;
        } else {
            throw new UnknownPin(this.globalId, pinNumber);
        }
    }

    @Override
    public void addObserver(int pinNumber, ComponentObserver observer) {
        List<ComponentObserver> circuitObservers = observers.get(pinNumber);
        if (circuitObservers == null) {
            circuitObservers = new ArrayList<>();
            circuitObservers.add(observer);
            observers.put(pinNumber, circuitObservers);
        } else {
            circuitObservers.add(observer);
        }
    }

    @Override
    public void removeObserver(int pinNumber, ComponentObserver observer) {
        List<ComponentObserver> circuitObservers = observers.get(pinNumber);
        if (observers == null) {
            return;
        }
        circuitObservers.remove(observer);
    }

    @Override
    public void notifyObserver(int pinNumber) throws UnknownPin, InterruptedException {
        List<ComponentObserver> circuitObservers = observers.get(pinNumber);
        if (circuitObservers == null) {
            ComponentLogger.logNoObserver(globalId, pinNumber);
            return;
        }
        boolean state = getPinState(pinNumber);
        for (ComponentObserver observer : circuitObservers) {
            ComponentLogger.logPinState(this.globalId, pinNumber, state);
            observer.update(state);
        }
    }

    @Override
    public ComponentPin getOutputPin(int pinNumber) throws UnknownPin {
        ComponentPin pin = outputs.get(pinNumber);
        if (pin == null) {
            throw new UnknownPin(this.globalId, pinNumber);
        }

        return pin;
    }

    @Override
    public ComponentPin getInputPin(int pinNumber) throws UnknownPin {
        ComponentPin pin = inputs.get(pinNumber);
        if (pin == null) {
            throw new UnknownPin(this.globalId, pinNumber);
        }

        return pin;
    }

    @Override
    public Set<ComponentPinState> getStates() {
        Set<ComponentPinState> states = new HashSet<>();

        for (Map.Entry<Integer, ComponentPin> entry : inputs.entrySet()) {
            states.add(new ComponentPinState(globalId, entry.getKey(),
                    PinStateMapper.toPinState(entry.getValue().getPin())));
        }

        for (Map.Entry<Integer, ComponentPin> entry : outputs.entrySet()) {
            states.add(new ComponentPinState(globalId, entry.getKey(),
                    PinStateMapper.toPinState(entry.getValue().getPin())));
        }

        return states;
    }

    @Override
    public void setState(ComponentPinState state) throws UnknownPin, InterruptedException {
        try {
            int pinNumber = state.pinId();
            setPinState(pinNumber, PinStateMapper.toBoolean(state.state()));
        } catch (UnknownPin e) {
            throw e;
        }
    }
}

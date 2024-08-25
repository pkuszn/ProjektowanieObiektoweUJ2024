package edu.uj.po.simulation.models.components;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentCommand;
import edu.uj.po.simulation.abstractions.ComponentObserver;
import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.models.ComponentPin;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseComponent implements Component {
    protected final int globalId;
    protected static final AtomicInteger counter = new AtomicInteger();
    protected Map<Integer, ComponentPin> pins;
    protected Set<ComponentObserver> observers;
    protected ComponentClass componentClass;
    protected String type;
    protected int tick;
    protected ComponentCommand command;
    
    public BaseComponent() {
        super();
        this.globalId = counter.incrementAndGet();
        this.observers = new HashSet<>();
    }

    @Override
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int getGlobalId() {
        return globalId;
    }

    @Override
    public ComponentClass getComponentClass() {
        return componentClass;
    }

    @Override
    public void setComponentClass(ComponentClass className) {
        this.componentClass = className;
    }

    @Override
    public Map<Integer, ComponentPin> getPins() {
        return pins;
    }

    @Override
    public void setPins(Map<Integer, ComponentPin> pins) {
        this.pins = pins;
    }

    @Override
    public void setState(ComponentPinState state) throws UnknownPin {
        ComponentPin pin = pins.get(state.pinId());
        if (pin == null) {
            throw new UnknownPin(state.componentId(), state.pinId());
        }

        pin.setState(state.state());
        notifyObserver(state.state());
    }

    @Override
    public PinState getPinState(int pinNumber) throws UnknownPin {
        ComponentPin componentPin = pins.get(pinNumber);
        if (componentPin == null) {
            throw new UnknownPin(this.getGlobalId(), pinNumber);
        }

        return componentPin.getState();
    }

    @Override
    public PinType getPinType(int pinNumber) throws UnknownPin {
        ComponentPin pin = pins != null ? pins.get(pinNumber) : null;
        if (pin == null) {
            throw new UnknownPin(this.globalId, pinNumber);
        }
        return pin.getPinType();
    }

    @Override
    public void addObserver(ComponentObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObserver(PinState state) throws UnknownPin {
        for (ComponentObserver observer : observers) {
            observer.update(state);
        }
    }

    @Override
    public Set<ComponentPinState> getStates() {
        Set<ComponentPinState> states = new HashSet<>();
        for (ComponentPin pin : this.pins.values()) {
            states.add(new ComponentPinState(globalId, pin.getPinNumber(), pin.getState()));
        }

        return states;
    }

    @Override
    public Set<ComponentObserver> getObservers() {
        return this.observers;
    }

    @Override
    public ComponentPin getPin(Integer pinNumber) throws UnknownPin {
        return this.pins.get(pinNumber);
    }

    @Override 
    public void applyCommand() {
        command.execute(this);    
    }

    @Override
    public void setCommand(ComponentCommand command) {
        this.command = command;
    }
}
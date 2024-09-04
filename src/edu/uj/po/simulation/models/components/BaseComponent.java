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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseComponent implements Component {
    protected final int globalId;
    protected static final AtomicInteger counter = new AtomicInteger();
    protected Map<Integer, ComponentPin> pins;
    protected HashMap<Integer, List<ComponentObserver>> observers;
    protected ComponentClass componentClass;
    protected String type;
    protected int tick;
    protected ComponentCommand command;
    protected boolean isDeactivated; 

    public BaseComponent() {
        super();
        this.globalId = counter.incrementAndGet();
        this.observers = new HashMap<>();
        this.isDeactivated = false;
    }

    @Override
    public String getType() {
        return type;
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
    public void addObserver(Integer pinNumber, ComponentObserver observer) {
        List<ComponentObserver> observerList = observers.get(pinNumber);
        
        if (observerList != null) {
            observerList.add(observer);
        } else {
            observerList = new ArrayList<>();
            observerList.add(observer);
            observers.put(pinNumber, observerList);
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
    public HashMap<Integer, List<ComponentObserver>> getObservers() {
        return this.observers;
    }

    @Override
    public ComponentPin getPin(Integer pinNumber) throws UnknownPin {
        return this.pins.get(pinNumber);
    }

    @Override
    public void applyCommand() {
        if (this.command != null && isDeactivated == false) {
            command.execute(this);
        }
    }

    @Override
    public void applyCommandTick() {
        if (this.command != null && isDeactivated == false) {
            command.executeTick(this);
        }
    }

    @Override
    public void setCommand(ComponentCommand command) {
        this.command = command;
    }

    @Override
    public void notifyObservers() {
        for (ComponentPin pin : pins.values()) {
            if (pin.getState() != PinState.UNKNOWN && pin.getPinType() == PinType.OUT) {
                pin.notifyObservers();
            }
        }
    }

    @Override
    public void deactivate() {
        this.isDeactivated = true;
    }

    @Override
    public void reactivate() {
        this.isDeactivated = false;
    }

    @Override
    public void resetPins() {
        for (ComponentPin pin : this.pins.values()) {
            pin.setStateTick(PinState.UNKNOWN);
        }
    }

    public void setType(String type) {
        this.type = type;
    }
}
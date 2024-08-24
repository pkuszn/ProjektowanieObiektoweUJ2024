package edu.uj.po.simulation.models.circuits;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentObserver;
import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.utils.ComponentLogger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class BaseComponent implements Component {
    protected final int globalId;
    protected static final AtomicInteger counter = new AtomicInteger();
    protected Map<Integer, ComponentPin> pins;
    protected Map<Integer, List<ComponentObserver>> observers;
    protected ComponentClass componentClass;
    protected String type;
    protected int tick;

    public BaseComponent() {
        super();
        this.globalId = counter.incrementAndGet();
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

    public Map<Integer, ComponentPin> getPins() {
        return pins;
    }

    @Override
    public void setPins(Map<Integer, ComponentPin> pins) {
        this.pins = pins;
    }

    public Map<Integer, List<ComponentObserver>> getObservers() {
        return observers;
    }

    @Override
    public void setState(ComponentPinState state) throws UnknownPin, InterruptedException {
        while (true) {
            // if (getBehaviour() == ComponentBehaviour.UNLOCK) {
            // System.out.println(this.globalId + " unlock");
            // ComponentPin componentPin = pins.get(state.pinId());
            // if (componentPin == null) {
            // throw new UnknownPin(this.getGlobalId(), state.pinId());
            // }

            // ComponentState componentState = new ComponentState(
            // this.globalId,
            // humanName,
            // type,
            // componentClass,
            // state.pinId(),
            // tick,
            // state.state(),
            // LocalDateTime.now());

            // this.addComponentState(this.globalId, componentState);
            // componentPin.setPin(PinStateMapper.toBoolean((state.state())));
            // notifyObserver(state.pinId());

            // break;
            // }
        }
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
        PinState state = getPinState(pinNumber);
        for (ComponentObserver observer : circuitObservers) {
            ComponentLogger.logPinState(this.globalId, pinNumber, state);
            observer.update(state);
        }
    }

    @Override
    public Set<ComponentPinState> getStates() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStates'");
    }
}
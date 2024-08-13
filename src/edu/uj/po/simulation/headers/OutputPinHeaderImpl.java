package edu.uj.po.simulation.headers;

import edu.uj.po.simulation.interfaces.AbstractComponent;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.OutputPinHeader;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.interfaces.enums.ComponentBehaviour;
import edu.uj.po.simulation.interfaces.enums.ComponentClass;
import edu.uj.po.simulation.interfaces.enums.PinType;
import edu.uj.po.simulation.interfaces.observers.ComponentObserver;
import edu.uj.po.simulation.pins.ComponentPin;
import edu.uj.po.simulation.utils.ComponentLogger;
import edu.uj.po.simulation.utils.PinStateMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class OutputPinHeaderImpl extends AbstractComponent implements OutputPinHeader {
    private final Map<Integer, ComponentPin> outputs;
    private final Map<Integer, List<ComponentObserver>> observers;
    private final ComponentClass componentClass;
    private final String humanName;
    private ComponentBehaviour behaviour;

    public ComponentBehaviour getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(ComponentBehaviour behaviour) {
        this.behaviour = behaviour;
    }

    public OutputPinHeaderImpl(int size) {
        super();
        outputs = new HashMap<>();
        observers = new HashMap<>();
        componentClass = ComponentClass.OUT;
        for (int i = 1; i <= size; i++) {
            outputs.put(i, new ComponentPin());
        }
        humanName = getClass().getSimpleName() + "_" + getGlobalId();
        behaviour = ComponentBehaviour.UNLOCK;
    }

    @Override
    public ComponentClass getComponentClass() {
        return componentClass;
    }

    @Override
    public void setState(ComponentPinState state) {
        while (true) {
            if (getBehaviour() == ComponentBehaviour.UNLOCK) {
                ComponentLogger.logPinState(state.componentId(), state.pinId(), PinStateMapper.toBoolean(state.state()));
                break;
            }
            System.out.println("Component " + humanName + " is locked...");
        }
    }

    @Override
    public PinType getPinType(int pinNumber) throws UnknownPin {
        return PinType.OUT;
    }

    @Override
    public boolean getPinState(int pinNumber) {
        return outputs.get(pinNumber).getPin();
    }

    @Override
    public String getHumanName() {
        return humanName;
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
    public void notifyObserver(int pinNumber) throws InterruptedException {
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
    public Set<ComponentPinState> getStates() {
        Set<ComponentPinState> states = new HashSet<>();

        for (Map.Entry<Integer, ComponentPin> entry : outputs.entrySet()) {
            states.add(new ComponentPinState(globalId, entry.getKey(),
                    PinStateMapper.toPinState(entry.getValue().getPin())));
        }

        return states;
    }
}
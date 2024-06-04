package edu.uj.po.simulation.headers;

import edu.uj.po.simulation.interfaces.AbstractComponent;
import edu.uj.po.simulation.interfaces.ComponentClass;
import edu.uj.po.simulation.interfaces.ComponentObserver;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.OutputPinHeader;
import edu.uj.po.simulation.interfaces.PinType;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.pins.ComponentPin;
import edu.uj.po.simulation.timer.TimeSimulationPropagator;
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
    private TimeSimulationPropagator propagator;

    public OutputPinHeaderImpl(int size) {
        super();
        outputs = new HashMap<>();
        observers = new HashMap<>();
        componentClass = ComponentClass.OUT;
        for (int i = 1; i <= size; i++) {
            outputs.put(i, new ComponentPin());
        }
        humanName = getClass().getSimpleName() + "_" + getGlobalId();
    }

    @Override
    public ComponentClass getComponentClass() {
        return componentClass;
    }

    @Override
    public void setState(ComponentPinState state) {
        return; // TODO
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
    public void notifyObserver(int pinNumber) {
        propagator = TimeSimulationPropagator.getInstance();
        List<ComponentObserver> circuitObservers = observers.get(pinNumber);
        if (circuitObservers == null) {
            System.out.println("No observer connected!");
            return;
        }
        boolean state = getPinState(pinNumber);
        for (ComponentObserver observer : circuitObservers) {
            System.out.println(propagator.getName() + " from OutputHeader: " + propagator.getCounter());
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
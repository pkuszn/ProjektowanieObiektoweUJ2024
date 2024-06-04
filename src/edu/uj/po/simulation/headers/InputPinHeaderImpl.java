package edu.uj.po.simulation.headers;

import edu.uj.po.simulation.interfaces.AbstractComponent;
import edu.uj.po.simulation.interfaces.ComponentClass;
import edu.uj.po.simulation.interfaces.ComponentObserver;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.InputPinHeader;
import edu.uj.po.simulation.interfaces.IntegratedCircuit;
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
import java.util.concurrent.CountDownLatch;

public class InputPinHeaderImpl extends AbstractComponent implements InputPinHeader {
    private final Map<Integer, ComponentPin> inputs;
    private final Map<Integer, List<ComponentObserver>> observers;
    private final ComponentClass componentClass;
    private final String humanName;
    private TimeSimulationPropagator propagator;

    public InputPinHeaderImpl(int size) {
        super();
        inputs = new HashMap<>();
        observers = new HashMap<>();
        componentClass = ComponentClass.IN;
        for (int i = 1; i <= size; i++) {
            inputs.put(i, new ComponentPin());
        }
        humanName = this.getClass().getSimpleName() + "_" + getGlobalId();
    }

    @Override
    public ComponentClass getComponentClass() {
        return componentClass;
    }

    private ComponentPin getInput(int pinNumber) {
        return inputs.get(pinNumber);
    }

    @Override
    public String getHumanName() {
        return humanName;
    }

    private boolean getPinState(int pinNumber) {
        return inputs.get(pinNumber).getPin();
    }


    @Override
    public PinType getPinType(int pinNumber) throws UnknownPin {
        return PinType.IN;
    }

    @Override
    public void setPinState(int pinNumber, boolean value) throws InterruptedException {
        ComponentPin pin = inputs.get(pinNumber);
        if (pin == null) {
            System.out.println("Pin not updated");
        }
        pin.setPin(value);
        notifyObserver(pinNumber);
    }

    public void connectIntegratedCircuitToPinHeader(int pinNumber, IntegratedCircuit integratedCircuit) throws UnknownPin {
        ComponentPin outputComponentPin = getInput(pinNumber);
        integratedCircuit.addObserver(pinNumber, (boolean newState) -> {
            outputComponentPin.setPin(newState);
        });
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
        propagator = TimeSimulationPropagator.getInstance();
        CountDownLatch latch = new CountDownLatch(1);
        propagator.setLatch(latch);

        latch.await();
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

        for(Map.Entry<Integer, ComponentPin> entry : inputs.entrySet()) {
            states.add(new ComponentPinState(globalId, entry.getKey(), PinStateMapper.toPinState(entry.getValue().getPin())));
        }

        return states;
    }

    @Override
    public void setState(ComponentPinState state) throws InterruptedException {
        int pinNumber = state.pinId();
        setPinState(pinNumber, PinStateMapper.toBoolean(state.state()));
    }
}

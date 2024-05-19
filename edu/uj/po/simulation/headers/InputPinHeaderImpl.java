package edu.uj.po.simulation.headers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.uj.po.simulation.interfaces.InputPinHeader;
import edu.uj.po.simulation.interfaces.IntegratedCircuit;
import edu.uj.po.simulation.interfaces.ComponentObserver;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.PinType;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.pins.ComponentPin;
import edu.uj.po.simulation.utils.PinGenerator;
import edu.uj.po.simulation.utils.PinStateMapper;

public class InputPinHeaderImpl implements InputPinHeader {
    private int globalId;
    private Map<Integer, ComponentPin> inputs;
    private Map<Integer, List<ComponentObserver>> observers;

    public InputPinHeaderImpl(int size) {
        super();
        inputs = new HashMap<>();
        observers = new HashMap<>();
        globalId = PinGenerator.generatePinNumber(0, 10000);
        for (int i = 1; i < size; i++) {
            inputs.put(i, new ComponentPin());
        }
    }

    private ComponentPin getInput(int pinNumber) {
        return inputs.get(pinNumber);
    }

    @Override
    public void setPinState(int pinNumber, boolean value) {
        ComponentPin pin = inputs.get(pinNumber);
        if (pin == null) {
            System.out.println("Pin not updated");
        }
        pin.setPin(value);
        notifyObserver(pinNumber);
    }

    private boolean getPinState(int pinNumber) {
        return inputs.get(pinNumber).getPin();
    }

    public void connectIntegratedCircuitToPinHeader(int pinNumber, IntegratedCircuit integratedCircuit) throws UnknownPin {
        ComponentPin outputComponentPin = getInput(pinNumber);
        integratedCircuit.addObserver(pinNumber, new ComponentObserver() {
            @Override
            public void update(boolean newState) {
                outputComponentPin.setPin(newState);
            }
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
    public int getGlobalId() {
        return globalId;
    }

    @Override
    public void notifyObserver(int pinNumber) {
        List<ComponentObserver> circuitObservers = observers.get(pinNumber);
        if (circuitObservers == null) {
            System.out.println("No observer connected!");
            return;
        }
        boolean state = getPinState(pinNumber);
        for (ComponentObserver observer : circuitObservers) {
            observer.update(state);
        }
    }

    @Override
    public PinType getPinType(int pinNumber) throws UnknownPin {
        return PinType.IN;
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
    public void setState(ComponentPinState state) {
        int pinNumber = state.pinId();
        setPinState(pinNumber, PinStateMapper.toBoolean(state.state()));
    }

    @Override
    public String printStates(int tick) {
        StringBuilder sb = new StringBuilder("ComponentId: " + this.globalId + " " + "Tick no. " + tick);
        Set<ComponentPinState> states = this.getStates();
        for(ComponentPinState state : states) {
            sb.append("pinNumber: " + state.pinId() + " " + "state: " + state.state() + "\n");
        }

        return sb.toString();
    }
}

package edu.uj.po.simulation.circuits;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.uj.po.simulation.interfaces.IntegratedCircuit;
import edu.uj.po.simulation.interfaces.ComponentObserver;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.PinType;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.pins.ComponentPin;
import edu.uj.po.simulation.utils.PinGenerator;
import edu.uj.po.simulation.utils.PinStateMapper;

/*
 * Description: https://eduinf.waw.pl/inf/prg/010_uc/7408.php
 */
public class IC74HC08 implements IntegratedCircuit {
    private Map<Integer, ComponentPin> inputs;
    private Map<Integer, ComponentPin> outputs;
    private Map<Integer, List<ComponentObserver>> observers;
    private int globalId;

    public IC74HC08() {
        super();
        inputs = new HashMap<>();
        outputs = new HashMap<>();
        observers = new HashMap<>();

        Integer[] inputPinNumbers = new Integer[]{1,2,4,5,9,10,12,13};
        
        Integer[] outputPinNumbers = new Integer[] {3,6,8,11};
        
        globalId = PinGenerator.generatePinNumber(1, 10000);

        for (Integer input : inputPinNumbers) {
            inputs.put(input, new ComponentPin());
        }

        for (Integer output : outputPinNumbers) {
            outputs.put(output, new ComponentPin());
        }
    }

    public void setPinState(int pinNumber, boolean value) {
        ComponentPin componentPin = inputs.get(pinNumber);
        componentPin.setPin(value);
        notifyObserver(pinNumber);
    }

    public boolean getPinState(int pinNumber) {
        return outputs.get(pinNumber).getPin();
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
            throw new UnknownPin(globalId, pinNumber);
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
    public int getGlobalId() {
        return this.globalId;
    }

    @Override
    public ComponentPin getOutputPin(int pinNumber) throws UnknownPin {
        ComponentPin pin = inputs.get(pinNumber);
        if (pin == null) {
            throw new UnknownPin(this.globalId, pinNumber);
        }

        return pin;
    }

    @Override
    public ComponentPin getInputPin(int pinNumber) throws UnknownPin {
        ComponentPin pin = outputs.get(pinNumber);
        if (pin == null) {
            throw new UnknownPin(this.globalId, pinNumber);
        }

        return pin;
    }

    @Override
    public Set<ComponentPinState> getStates() {
        Set<ComponentPinState> states = new HashSet<>();

        for(Map.Entry<Integer, ComponentPin> entry : inputs.entrySet()) {
            states.add(new ComponentPinState(globalId, entry.getKey(), PinStateMapper.toPinState(entry.getValue().getPin()))); 
        }

        for(Map.Entry<Integer, ComponentPin> entry : outputs.entrySet()) {
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

package edu.uj.po.simulation.abstractions;

import edu.uj.po.simulation.abstractions.observers.ComponentObserver;
import edu.uj.po.simulation.enums.ComponentBehaviour;
import edu.uj.po.simulation.enums.ComponentClass;
import edu.uj.po.simulation.enums.PinType;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.UnknownPin;
import java.util.Set;

public interface Component {
    public void addObserver(int pinNumber, ComponentObserver observer) throws UnknownPin;
    public void removeObserver(int pinNumber, ComponentObserver observer) throws UnknownPin;
    public void notifyObserver(int pinNumber) throws UnknownPin, InterruptedException;
    public PinType getPinType(int pinNumber) throws UnknownPin;
    public void setState(ComponentPinState state) throws UnknownPin, InterruptedException;
    public int getGlobalId();
    public Set<ComponentPinState> getStates();
    public ComponentClass getComponentClass();
    public String getHumanName();
    public void setBehaviour(ComponentBehaviour behaviour);
    public String getComponentType();
    public void setTick(int tick);
}

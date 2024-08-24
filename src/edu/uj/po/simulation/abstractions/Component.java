package edu.uj.po.simulation.abstractions;

import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.models.ComponentPin;
import java.util.Map;
import java.util.Set;

public interface Component {
    public void addObserver(int pinNumber, ComponentObserver observer) throws UnknownPin;
    public void removeObserver(int pinNumber, ComponentObserver observer) throws UnknownPin;
    public void notifyObserver(int pinNumber) throws UnknownPin, InterruptedException;
    public PinType getPinType(int pinNumber) throws UnknownPin;
    public void setState(ComponentPinState state) throws UnknownPin, InterruptedException;
    public PinState getPinState(int pinNumber) throws UnknownPin;
    public int getGlobalId();
    public Set<ComponentPinState> getStates();
    public ComponentClass getComponentClass();
    public void setComponentClass(ComponentClass className);
    public String getType();
    public void setPins(Map<Integer, ComponentPin> pins);
}

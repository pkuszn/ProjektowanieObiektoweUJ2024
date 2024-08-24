package edu.uj.po.simulation.abstractions;

import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.models.ComponentPin;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public interface Component {
    public void addObserver(ComponentObserver observer) throws UnknownPin;
    // public void removeObserver(int pinNumber, ComponentObserver observer) throws UnknownPin;
    public void notifyObserver(PinState state) throws UnknownPin;
    public PinType getPinType(int pinNumber) throws UnknownPin;
    public void setState(ComponentPinState state) throws UnknownPin;
    public PinState getPinState(int pinNumber) throws UnknownPin;
    public int getGlobalId();
    public ComponentClass getComponentClass();
    public void setComponentClass(ComponentClass className);
    public String getType();
    public void setPins(Map<Integer, ComponentPin> pins);
    public Map<Integer, ComponentPin> getPins();
    public Consumer<Void> getConsumer();
    public void setConsumer(Consumer<Void> consumer);
    public Set<ComponentPinState> getStates();
    public Set<ComponentObserver> getObservers();
}

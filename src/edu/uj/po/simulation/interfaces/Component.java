package edu.uj.po.simulation.interfaces;

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
}
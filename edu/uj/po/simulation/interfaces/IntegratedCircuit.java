package edu.uj.po.simulation.interfaces;

import java.util.List;

public interface IntegratedCircuit {
    public boolean[] state = null;
    public List<CircuitObserver> observers = null;
    public void setPinState(int pinNumber, boolean value) throws UnknownPin;
    public void addObserver(int pinNumber, CircuitObserver observer) throws UnknownPin;
    public void removeObserver(int pinNumber, CircuitObserver observer) throws UnknownPin;
    public void notifyObserver(int pinNumber) throws UnknownPin;
    public int getGlobalId();
    public PinType getPinType(int pinNumber) throws UnknownPin;
}

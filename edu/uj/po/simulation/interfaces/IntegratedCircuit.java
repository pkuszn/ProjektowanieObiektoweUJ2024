package edu.uj.po.simulation.interfaces;

import java.util.List;

public interface IntegratedCircuit {
    public boolean[] state = null;
    public List<CircuitObserver> observers = null;
    public void setPinState(int pinNumber, boolean value);
    public void addObserver(int pinNumber, CircuitObserver observer);
    public void removeObserver(int pinNumber, CircuitObserver observer);
    public void notifyObserver(int pinNumber);
}

package edu.uj.po.simulation.interfaces;

import java.util.List;

public interface IntegratedCircuit {
    public boolean[] state = null;
    public List<CircuitObserver> observers = null;
    public void addObserver(CircuitObserver observer);
    public void removeObserver(CircuitObserver observer);
    public void notifyObserver();
}

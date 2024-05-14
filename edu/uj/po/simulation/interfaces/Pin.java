package edu.uj.po.simulation.interfaces;

public interface Pin {
    boolean getPin();
    void setPin(boolean value);
    void addObserver(ComponentPinObserver observer);
    void removeObserver(ComponentPinObserver observer);
    void notifyObservers();
}

package edu.uj.po.simulation.interfaces;

public interface OutputPinHeader {
    int getId();
    void setPinState(int pinNumber, boolean value);
    boolean getPinState(int pinNumber);
    void addObserver(int pinNumber, OutputPinHeaderObserver observer);
    void removeObserver(int pinNumber, OutputPinHeaderObserver observer);
    void notifyObservers(int pinNumber);
}

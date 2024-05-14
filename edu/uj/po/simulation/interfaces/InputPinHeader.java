package edu.uj.po.simulation.interfaces;

public interface InputPinHeader {
    int getId();
    void setPinState(int pinNumber, boolean value);
    boolean getPinState(int pinNumber);
    void addObserver(int pinNumber, InputPinHeaderObserver observer);
    void removeObserver(int pinNumber, InputPinHeaderObserver observer);
    void notifyObservers(int pinNumber);
}

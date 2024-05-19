package edu.uj.po.simulation.interfaces;

public interface InputPinHeader {
    int getId();
    void setPinState(int pinNumber, boolean value);
    boolean getPinState(int pinNumber) throws UnknownPin;
    void addObserver(int pinNumber, InputPinHeaderObserver observer) throws UnknownPin;
    void removeObserver(int pinNumber, InputPinHeaderObserver observer) throws UnknownPin;
    void notifyObservers(int pinNumber) throws UnknownPin;
}

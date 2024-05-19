package edu.uj.po.simulation.interfaces;

public interface OutputPinHeader {
    int getId();
    void setPinState(int pinNumber, boolean value) throws UnknownPin;
    boolean getPinState(int pinNumber) throws UnknownPin;
    void addObserver(int pinNumber, OutputPinHeaderObserver observer) throws UnknownPin;
    void removeObserver(int pinNumber, OutputPinHeaderObserver observer) throws UnknownPin;
    void notifyObservers(int pinNumber) throws UnknownPin;
}

package edu.uj.po.simulation.interfaces;

public interface Component {
    public void addObserver(int pinNumber, Observer observer) throws UnknownPin;
    public void removeObserver(int pinNumber, Observer observer) throws UnknownPin;
    public void notifyObserver(int pinNumber) throws UnknownPin;
    public PinType getPinType(int pinNumber) throws UnknownPin;
    public int getGlobalId();
}

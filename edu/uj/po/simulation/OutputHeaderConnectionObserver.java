package edu.uj.po.simulation;

import edu.uj.po.simulation.interfaces.OutputPinHeaderObserver;
import edu.uj.po.simulation.utils.PinGenerator;

public class OutputHeaderConnectionObserver implements OutputPinHeaderObserver {
    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    private int pinNumber;
    public int getPinNumber() {
        return pinNumber;
    }
    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }
    public OutputHeaderConnectionObserver(int pinNumber) {
        super();
        this.pinNumber = pinNumber;
        id = PinGenerator.generatePinNumber(0, 10000);
    }
    @Override
    public void update(boolean state) {
        System.out.println("pin: " + pinNumber + " " + "state: " + state);
    }
}

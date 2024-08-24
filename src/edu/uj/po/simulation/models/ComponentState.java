package edu.uj.po.simulation.models;

import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.interfaces.PinState;
import java.time.LocalDateTime;

public class ComponentState {
    private int globalId;
    private String humanName;
    private String type;
    private ComponentClass componentClass;
    private int pinNumber;
    private int tickNumber;
    private PinState pinState;
    private LocalDateTime date;

    public ComponentState(
        int globalId, 
        String humanName, 
        String type, 
        ComponentClass componentClass, 
        int pinNumber, 
        int tickNumbers, 
        PinState pinStates, 
        LocalDateTime dates) {
        this.globalId = globalId;
        this.humanName = humanName;
        this.type = type;
        this.componentClass = componentClass;
        this.pinNumber = pinNumber;
        this.tickNumber = tickNumbers;
        this.pinState = pinStates;
        this.date = dates;
    }
    
    public int getGlobalId() {
        return globalId;
    }
    public void setGlobalId(int globalId) {
        this.globalId = globalId;
    }
    public String getHumanName() {
        return humanName;
    }
    public void setHumanName(String humanName) {
        this.humanName = humanName;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public ComponentClass getComponentClass() {
        return componentClass;
    }
    public void setComponentClass(ComponentClass componentClass) {
        this.componentClass = componentClass;
    }
    public int getTickNumber() {
        return tickNumber;
    }
    public void setTickNumber(int tickNumber) {
        this.tickNumber = tickNumber;
    }
    public int getPinNumber() {
        return pinNumber;
    }
    public void setPinNumber(int pinNumber) {
        this.pinNumber = pinNumber;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public PinState getPinState() {
        return pinState;
    }
    public void setPinState(PinState pinState) {
        this.pinState = pinState;
    }
}

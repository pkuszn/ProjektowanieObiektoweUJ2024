package edu.uj.po.simulation.recorder;

import edu.uj.po.simulation.interfaces.enums.ComponentClass;
import java.time.LocalDateTime;

public class TickState {
    private int globalId;
    private String humanName;
    private String type;
    private ComponentClass componentClass;
    private int tickNumber;
    private int pinNumber;
    private LocalDateTime date;

    public TickState(int globalId, String humanName, String type, ComponentClass componentClass, int tickNumber,
            int pinNumber, LocalDateTime date) {
        this.globalId = globalId;
        this.humanName = humanName;
        this.type = type;
        this.componentClass = componentClass;
        this.tickNumber = tickNumber;
        this.pinNumber = pinNumber;
        this.date = date;
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
}

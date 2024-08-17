package edu.uj.po.simulation;

import edu.uj.po.simulation.interfaces.Component;
import edu.uj.po.simulation.interfaces.enums.ComponentBehaviour;
import edu.uj.po.simulation.interfaces.observers.PropagatorObserver;
import edu.uj.po.simulation.utils.ConfigReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TimePropagator implements Runnable{
    private static long TIME_DELAY = 0;
    private static final String PROPAGATION_DELAY_KEY = "PROPAGATION_DELAY";
    private final List<PropagatorObserver> componentBehaviours;
    private int ticks = 0;

    public TimePropagator() {
        super();
        Properties prop = ConfigReader.loadProperties();
        TIME_DELAY = Long.parseLong(prop.getProperty(PROPAGATION_DELAY_KEY)); 
        if (TIME_DELAY == 0) { //DEFAULT value;
            TIME_DELAY = 5000;
        }
        componentBehaviours = new ArrayList<>();
    }

    public int getTicks() {
        return ticks;
    }

    public void setTicks(int ticks) {
        this.ticks = ticks;
    }
    
    public void propagate() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            ticks++;
            setLock(ComponentBehaviour.LOCK);
            System.out.println("Tick" + ticks + " components locked...");
            Thread.sleep(TIME_DELAY);
            setLock(ComponentBehaviour.UNLOCK);
            System.out.println("Tick" + ticks + " components unlocked...");
        }
        System.out.println("Propagator has been disabled...");
    }

    public synchronized void addObserver(Component component) {
        if (component == null) {
            System.out.println("Component is null!");
        }

        componentBehaviours.add((value, ticks) -> {
            component.setBehaviour(value);
            component.setTick(ticks);
        });

    }

    private void setLock(ComponentBehaviour behaviour) {
        for (PropagatorObserver component : componentBehaviours) {
            component.update(behaviour, ticks);
        }
    }

    @Override
    public void run() {
        try {
            propagate();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
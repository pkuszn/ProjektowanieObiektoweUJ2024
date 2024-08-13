package edu.uj.po.simulation;

import java.util.ArrayList;
import java.util.List;

import edu.uj.po.simulation.interfaces.Component;
import edu.uj.po.simulation.interfaces.enums.ComponentBehaviour;
import edu.uj.po.simulation.interfaces.observers.PropagatorObserver;

public class TimePropagator implements Runnable{
    private static final long TIME_DELAY = 5000; // In miliseconds...
    private static int ticks = 0;
    private List<PropagatorObserver> componentBehaviours;
    private boolean enabled = true;

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public TimePropagator() {
        super();
        componentBehaviours = new ArrayList<>();
    }

    public void propagate() throws InterruptedException {
        while (this.enabled) {
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

        componentBehaviours.add(value -> {
            component.setBehaviour(value);
        });

    }

    private void setLock(ComponentBehaviour behaviour) {
        for (PropagatorObserver component : componentBehaviours) {
            component.update(behaviour);
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

package edu.uj.po.simulation.managers;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.UnknownStateException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OptimizationManager {
    private final SimulationManager manager;

    public OptimizationManager(SimulationManager manager) {
        super();
        this.manager = manager;
    }

    public Set<Integer> optimize(Set<ComponentPinState> states0, int ticks) throws UnknownStateException {
        Set<Integer> removableComponents = new HashSet<>();
        Map<Integer, Set<ComponentPinState>> baseSimulation = manager.simulation(states0, ticks);

        Set<Component> componentsSnapshot = new HashSet<>(manager.getComponents().values());
        for (Component component : componentsSnapshot) {
            int componentId = component.getGlobalId();
            removeComponent(componentId);
            try {
                Map<Integer, Set<ComponentPinState>> modifiedSimulation = manager.simulation(states0, ticks);

                if (baseSimulation.equals(modifiedSimulation)) {
                    removableComponents.add(componentId);
                }
            } finally {
                addComponent(component);
            }
        }

        return removableComponents;
    }

    public void removeComponent(int componentId) {
        manager.getComponents().remove(componentId);
    }

    public void addComponent(Component component) {
        manager.getComponents().put(component.getGlobalId(), component);
    }
}

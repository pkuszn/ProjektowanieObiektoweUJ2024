package edu.uj.po.simulation.managers;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.UnknownStateException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OptimizationManager {
    private final SimulationManager simulationManager;

    public OptimizationManager(SimulationManager simulationManager) {
        super();
        this.simulationManager = simulationManager;
    }

    public Set<Integer> optimize(Set<ComponentPinState> states0, int ticks) throws UnknownStateException {
        Set<Integer> removableComponents = new HashSet<>();
        Map<Integer, Set<ComponentPinState>> baseSimulation = simulationManager.simulation(states0, ticks);

        Set<Component> componentsSnapshot = new HashSet<>(simulationManager.getComponents().values());
        for (Component component : componentsSnapshot) {
            int componentId = component.getGlobalId();
            deactivateComponent(component);
            try {
                Map<Integer, Set<ComponentPinState>> modifiedSimulation = simulationManager.simulation(states0, ticks);

                if (baseSimulation.equals(modifiedSimulation)) {
                    removableComponents.add(componentId);
                }
            } finally {
                reactivateComponent(component);
                simulationManager.resetAllPins();
                simulationManager.stationaryState(states0);
            }
        }

        return removableComponents;
    }

    private void deactivateComponent(Component component) {
        component.deactivate();
    }

    public void reactivateComponent(Component component) {
        component.reactivate();
    }

    public void removeComponent(int componentId) {
        simulationManager.getComponents().remove(componentId);
    }

    public void addComponent(Component component) {
        simulationManager.getComponents().put(component.getGlobalId(), component);
    }
}

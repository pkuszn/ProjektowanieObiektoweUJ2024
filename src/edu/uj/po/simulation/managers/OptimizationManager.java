package edu.uj.po.simulation.managers;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.UnknownStateException;

import java.util.HashMap;
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
        Map<Integer, Component> componentsSnapshopMap = new HashMap<>(simulationManager.getComponents());
        Set<ComponentPinState> inputsSnapshot = getAllInputs(componentsSnapshopMap);
        Set<Component> componentsSnapshot = new HashSet<>(componentsSnapshopMap.values());
        Map<Integer, Set<ComponentPinState>> baseSimulation = simulationManager.simulation(states0, ticks);
        simulationManager.stationaryState(inputsSnapshot);
        for (Component component : componentsSnapshot) {
            if (component.getComponentClass() == ComponentClass.IN || component.getComponentClass() == ComponentClass.OUT) {
                continue;
            }
            int componentId = component.getGlobalId();
            try {
                deactivateComponent(component);
                Map<Integer, Set<ComponentPinState>> modifiedSimulation = simulationManager.simulation(states0, ticks);

                if (baseSimulation.equals(modifiedSimulation)) {
                    removableComponents.add(componentId);
                }
            } finally {
                reactivateComponent(component);
                simulationManager.stationaryState(inputsSnapshot);
            }
        }

        return removableComponents;
    }

    private void deactivateComponent(Component component) {
        component.deactivate();
    }

    private void reactivateComponent(Component component) {        
        simulationManager.resetAllPins();
        component.reactivate();
    }

    private Set<ComponentPinState> getAllInputs(Map<Integer, Component> map) {
        Set<ComponentPinState> inputs = new HashSet<>();
        for (Component component : map.values()) {
            if (component.getComponentClass() == ComponentClass.IN) {
                inputs.addAll(component.getStates());
            }
        }
        return inputs;
    }
}

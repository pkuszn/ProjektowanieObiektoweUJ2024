package edu.uj.po.simulation;

import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.ShortCircuitException;
import edu.uj.po.simulation.interfaces.UnknownChip;
import edu.uj.po.simulation.interfaces.UnknownComponent;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.interfaces.UnknownStateException;
import edu.uj.po.simulation.interfaces.UserInterface;
import edu.uj.po.simulation.managers.ComponentManager;
import edu.uj.po.simulation.managers.OptimizationManager;
import edu.uj.po.simulation.managers.SimulationManager;
import java.util.Map;
import java.util.Set;

public class Simulation implements UserInterface {
    private final SimulationManager simulationManager;
    private final OptimizationManager optimizationManager;
    private final ComponentManager componentManager;

    public Simulation() {
        super();
        this.simulationManager = new SimulationManager();
        this.optimizationManager = new OptimizationManager(simulationManager);
        this.componentManager = new ComponentManager(simulationManager);
    }

    @Override
    public int createChip(int code) throws UnknownChip, UnknownPin {
        return componentManager.createChip(code);
    }

    @Override
    public int createInputPinHeader(int size) {
        return componentManager.createInputPinHeader(size);
    }

    @Override
    public int createOutputPinHeader(int size) {
        return componentManager.createOutputPinHeader(size);
    }

    @Override
    public void connect(int component1, int pin1, int component2, int pin2)
            throws UnknownComponent, UnknownPin, ShortCircuitException {
        componentManager.connect(component1, pin1, component2, pin2);
    }

    @Override
    public void stationaryState(Set<ComponentPinState> states) throws UnknownStateException {
        simulationManager.stationaryState(states);
    }

    @Override
    public Map<Integer, Set<ComponentPinState>> simulation(Set<ComponentPinState> states0, int ticks)
            throws UnknownStateException {
        return simulationManager.simulation(states0, ticks);
    }

    @Override
    public Set<Integer> optimize(Set<ComponentPinState> states0, int ticks) throws UnknownStateException {
        return optimizationManager.optimize(states0, ticks);
    }
}

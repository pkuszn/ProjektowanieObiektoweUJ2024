package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.ShortCircuitException;
import edu.uj.po.simulation.interfaces.UnknownChip;
import edu.uj.po.simulation.interfaces.UnknownComponent;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.interfaces.UnknownStateException;
import edu.uj.po.simulation.managers.ComponentManager;
import edu.uj.po.simulation.managers.SimulationManager;
import java.util.HashSet;
import java.util.Set;

public class Test_Simulation extends TestBase {
    private final ComponentManager componentManager;
    private final SimulationManager simulationManager;

    public Test_Simulation() {
        super();
        this.simulationManager = new SimulationManager();
        this.componentManager = new ComponentManager(simulationManager);
    }

    protected void testComponents()
            throws UnknownChip, UnknownPin, UnknownComponent, ShortCircuitException, UnknownStateException {
        test_stationary_state_valid();
    }

    private void test_stationary_state_valid()
            throws UnknownChip, UnknownPin, UnknownComponent, ShortCircuitException, UnknownStateException {
        int input = componentManager.createInputPinHeader(4);
        int component = componentManager.createChip(7408);
        int output = componentManager.createOutputPinHeader(4);

        componentManager.connect(input, 1, component, 1);
        componentManager.connect(input, 1, component, 2);
        componentManager.connect(component, 3, output, 1);
        componentManager.connect(component, 3, output, 2);

        Set<ComponentPinState> states = new HashSet<>();
        states.add(new ComponentPinState(input, 1, PinState.HIGH));
        states.add(new ComponentPinState(input, 2, PinState.LOW));

        simulationManager.stationaryState(states);

        try {
            assert verifyOutput(simulationManager.getComponents().get(output).getPinState(1), PinState.HIGH) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
        try {
            assert verifyOutput(simulationManager.getComponents().get(output).getPinState(2), PinState.HIGH) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
    }

    @Override
    protected void testComponent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testComponent'");
    }
}

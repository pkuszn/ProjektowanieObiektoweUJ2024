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
import java.util.Map;
import java.util.Set;

public class Test_Simulation extends TestBase {
    private final SimulationManager simulationManager;
    private final ComponentManager componentManager;
    public Test_Simulation() {
        super();
        this.simulationManager = new SimulationManager();
        this.componentManager = new ComponentManager(simulationManager);
    }

    @Override
    protected void testComponent() {
        throw new UnsupportedOperationException("Unimplemented method 'testComponent'");
    }

    public void testComponents() throws UnknownChip, UnknownStateException, UnknownPin, ShortCircuitException, UnknownComponent {
        test_simulation_simple_circuit();
    }


    private void test_simulation_simple_circuit() throws UnknownChip, UnknownStateException, UnknownPin, ShortCircuitException, UnknownComponent {
        this.simulationManager.resetComponents();
        int chipIn1 = componentManager.createInputPinHeader(2);
		int chip7400 = componentManager.createChip(7400);
		int chipOut1 = componentManager.createOutputPinHeader(1);

		componentManager.connect(chipIn1, 1, chip7400, 1);
		componentManager.connect(chipIn1, 2, chip7400, 2);
		componentManager.connect(chip7400, 3, chipOut1, 1);

		Set<ComponentPinState> states = new HashSet<>();
		states.add(new ComponentPinState(chipIn1, 1, PinState.HIGH));
		states.add(new ComponentPinState(chipIn1, 2, PinState.LOW));

		simulationManager.stationaryState(states);

		Set<ComponentPinState> states0 = new HashSet<>();
		states0.add(new ComponentPinState(chipIn1, 1, PinState.HIGH));
		states0.add(new ComponentPinState(chipIn1, 2, PinState.HIGH));

		Map<Integer, Set<ComponentPinState>> result = simulationManager.simulation(states0, 3);

        PinState state1 = result.get(0).stream()
                .filter(a -> a.componentId() == 3)
                .findFirst()
                .orElseThrow()
                .state();

        PinState state2 = result.get(1).stream()
                .filter(a -> a.componentId() == 3)
                .findFirst()
                .orElseThrow()
                .state();

        PinState state3 = result.get(2).stream()
                .filter(a -> a.componentId() == 3)
                .findFirst()
                .orElseThrow()
                .state();
        try {
            assert verifyOutput(state1, PinState.HIGH) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }

        try {
            assert verifyOutput(state2, PinState.LOW) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }

        try {
            assert verifyOutput(state3, PinState.LOW) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
    }
}
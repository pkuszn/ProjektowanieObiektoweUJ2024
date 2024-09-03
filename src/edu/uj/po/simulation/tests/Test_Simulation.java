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

    public void testComponents()
            throws UnknownChip, UnknownStateException, UnknownPin, ShortCircuitException, UnknownComponent {
        // test_simulation_simple_circuit();
        test_simulation_complex_circuit();
        // test_simulation_max_complex_circuit();
    }

    private void test_simulation_simple_circuit()
            throws UnknownChip, UnknownStateException, UnknownPin, ShortCircuitException, UnknownComponent {
        this.simulationManager.resetComponents();
        this.componentManager.resetComponents();
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

        PinState state4 = result.get(3).stream()
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

        try {
            assert verifyOutput(state4, PinState.LOW) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
    }

        private void test_simulation_complex_circuit() throws UnknownChip, UnknownStateException, UnknownPin, ShortCircuitException, UnknownComponent {
        this.simulationManager.resetComponents();
        this.componentManager.resetComponents();
        int chipIn1 = componentManager.createInputPinHeader(3); // 1
		int chip7400 = componentManager.createChip(7400); //2
		int chip7402 = componentManager.createChip(7402); // 3
		int chip7404 = componentManager.createChip(7404); // 4
		int chipOut1 = componentManager.createOutputPinHeader(2); // 5

		componentManager.connect(chipIn1, 1, chip7400, 1);
		componentManager.connect(chipIn1, 2, chip7400, 2);
		componentManager.connect(chipIn1, 3, chip7402, 9);

		componentManager.connect(chip7400, 3, chip7402, 8);
		componentManager.connect(chip7400, 3, chipOut1, 1);

		componentManager.connect(chip7402, 10, chip7404, 3);

		componentManager.connect(chip7404, 4, chipOut1, 2);

		Set<ComponentPinState> states = new HashSet<>();
		states.add(new ComponentPinState(chipIn1, 1, PinState.HIGH));
		states.add(new ComponentPinState(chipIn1, 2, PinState.LOW  ));
		states.add(new ComponentPinState(chipIn1, 3, PinState.HIGH));

		simulationManager.stationaryState(states);

		Set<ComponentPinState> states0 = new HashSet<>();
		states0.add(new ComponentPinState(chipIn1, 1, PinState.HIGH));
		states0.add(new ComponentPinState(chipIn1, 2, PinState.HIGH));
		states0.add(new ComponentPinState(chipIn1, 3, PinState.LOW));

		int tick = 3;
		Map<Integer, Set<ComponentPinState>> result = simulationManager.simulation(states0, tick);

        for (int i = 0; i<=tick; i++) {
            if (i == 0) {
                try {
                    PinState state1 = result.get(i).stream()
						.filter(state -> state.componentId() == chipOut1 && state.pinId() == 1)
						.findFirst().orElseThrow().state();

                    assert verifyOutput(state1, PinState.HIGH) == true;
                    System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "tick: " + i + " pinNumber: " + 1));
        
                } catch (AssertionError e) {
                    System.out.println(
                            failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
                }

                try {
                    PinState state2 = result.get(i).stream()
						.filter(state -> state.componentId() == chipOut1 && state.pinId() == 2)
						.findFirst().orElseThrow().state();

                    assert verifyOutput(state2, PinState.HIGH) == true;
                    System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "tick: " + i + " pinNumber: " + 2));
        
                } catch (AssertionError e) {
                    System.out.println(
                            failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
                }
            }
            if (i == 1) {
                try {
                    PinState state1 = result.get(i).stream()
						.filter(state -> state.componentId() == chipOut1 && state.pinId() == 1)
						.findFirst().orElseThrow().state();

                    assert verifyOutput(state1, PinState.LOW) == true;
                    System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "tick: " + i + " pinNumber: " + 1));
        
                } catch (AssertionError e) {
                    System.out.println(
                            failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
                }

                try {
                    PinState state2 = result.get(i).stream()
						.filter(state -> state.componentId() == chipOut1 && state.pinId() == 2)
						.findFirst().orElseThrow().state();

                    assert verifyOutput(state2, PinState.HIGH) == true;
                    System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "tick: " + i + " pinNumber: " + 2));
        
                } catch (AssertionError e) {
                    System.out.println(
                            failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
                }
            }
            if (i == 2) {
                try {
                    PinState state1 = result.get(i).stream()
						.filter(state -> state.componentId() == chipOut1 && state.pinId() == 1)
						.findFirst().orElseThrow().state();

                    assert verifyOutput(state1, PinState.LOW) == true;
                    System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "tick: " + i + " pinNumber: " + 1));
        
                } catch (AssertionError e) {
                    System.out.println(
                            failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
                }

                try {
                    PinState state2 = result.get(i).stream()
						.filter(state -> state.componentId() == chipOut1 && state.pinId() == 2)
						.findFirst().orElseThrow().state();

                    assert verifyOutput(state2, PinState.HIGH) == true;
                    System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "tick: " + i + " pinNumber: " + 2));
        
                } catch (AssertionError e) {
                    System.out.println(
                            failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
                }
            }
            if (i == 3) {
                try {
                    PinState state1 = result.get(i).stream()
						.filter(state -> state.componentId() == chipOut1 && state.pinId() == 1)
						.findFirst().orElseThrow().state();

                    assert verifyOutput(state1, PinState.LOW) == true;
                    System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "tick: " + i + " pinNumber: " + 1));
        
                } catch (AssertionError e) {
                    System.out.println(
                            failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
                }

                try {
                    PinState state2 = result.get(i).stream()
						.filter(state -> state.componentId() == chipOut1 && state.pinId() == 2)
						.findFirst().orElseThrow().state();

                    assert verifyOutput(state2, PinState.LOW) == true;
                    System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "tick: " + i + " pinNumber: " + 2));
        
                } catch (AssertionError e) {
                    System.out.println(
                            failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
                }
            }
        }
    }

    private void test_simulation_max_complex_circuit() throws UnknownChip, UnknownStateException, UnknownPin, ShortCircuitException, UnknownComponent {
        this.simulationManager.resetComponents();
        this.componentManager.resetComponents();
        int chipIn0 = componentManager.createInputPinHeader(1);
		int chipIn1 = componentManager.createInputPinHeader(2);
		int chipIn2 = componentManager.createInputPinHeader(3);
		int chip7400 = componentManager.createChip(7400);
		int chip7402 = componentManager.createChip(7402);
		int chip7404 = componentManager.createChip(7404);
		int chip7408 = componentManager.createChip(7408);
		int chip7410 = componentManager.createChip(7410);
		int chipOut0 = componentManager.createOutputPinHeader(1);
		int chipOut1 = componentManager.createOutputPinHeader(2);
		System.out.println("chipIn2 : " + chipIn2);
		System.out.println("chipOut1 : " + chipOut1);
		componentManager.connect(chipIn0, 1, chip7410, 9);

		componentManager.connect(chipIn1, 1, chip7410, 10);
		componentManager.connect(chipIn1, 2, chip7410, 11);

		componentManager.connect(chipIn2, 1, chip7408, 5);
		componentManager.connect(chipIn2, 2, chip7402, 2);
		componentManager.connect(chipIn2, 3, chip7402, 3);

		componentManager.connect(chip7410, 8, chip7408, 4);

		componentManager.connect(chip7408, 6, chip7404, 13);
		componentManager.connect(chip7408, 6, chipOut1, 2);

		componentManager.connect(chip7404, 12, chip7400, 4);

		componentManager.connect(chip7402, 1, chip7400, 5);

		componentManager.connect(chip7400, 6, chipOut0, 1);
		componentManager.connect(chip7400, 6, chipOut1, 1);

		Set<ComponentPinState> states = new HashSet<>();
		states.add(new ComponentPinState(chipIn0, 1, PinState.HIGH));
		states.add(new ComponentPinState(chipIn1, 1, PinState.HIGH));
		states.add(new ComponentPinState(chipIn1, 2, PinState.LOW));
		states.add(new ComponentPinState(chipIn2, 1, PinState.LOW));
		states.add(new ComponentPinState(chipIn2, 2, PinState.LOW));
		states.add(new ComponentPinState(chipIn2, 3, PinState.HIGH));

		simulationManager.stationaryState(states);

		Set<ComponentPinState> states0 = new HashSet<>();
		states0.add(new ComponentPinState(chipIn0, 1, PinState.LOW));
		states0.add(new ComponentPinState(chipIn1, 1, PinState.LOW));
		states0.add(new ComponentPinState(chipIn1, 2, PinState.HIGH));
		states0.add(new ComponentPinState(chipIn2, 1, PinState.HIGH));
		states0.add(new ComponentPinState(chipIn2, 2, PinState.HIGH));
		states0.add(new ComponentPinState(chipIn2, 3, PinState.LOW));

		int tick = 3;
		System.out.println("SIMULATON ****************");
		Map<Integer, Set<ComponentPinState>> result = simulationManager.simulation(states0, tick);
		result.forEach((key, value) -> {
			System.out.println("Key: " + key);
			for(ComponentPinState c : value) if(c.state() != PinState.UNKNOWN) System.out.println(c);
		});
        
        for (int i = 0; i<=tick; i++) {
            try {
                PinState state0 = result.get(i).stream()
                    .filter(state -> state.componentId() == chipOut0 && state.pinId() == 1)
                    .findFirst().orElseThrow().state();
        
                assert verifyOutput(state0, PinState.HIGH) == true;
                System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "tick: " + i + " pinNumber: " + 1 + " (chipOut0)"));
        
            } catch (AssertionError e) {
                System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "tick: " + i + " pinNumber: " + 1 + " (chipOut0): " + e.getMessage()));
            }
        
            try {
                PinState state1 = result.get(i).stream()
                    .filter(state -> state.componentId() == chipOut1 && state.pinId() == 1)
                    .findFirst().orElseThrow().state();
        
                assert verifyOutput(state1, PinState.HIGH) == true;
                System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "tick: " + i + " pinNumber: " + 1 + " (chipOut1)"));
        
            } catch (AssertionError e) {
                System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "tick: " + i + " pinNumber: " + 1 + " (chipOut1): " + e.getMessage()));
            }
        
            try {
                PinState state2 = result.get(i).stream()
                    .filter(state -> state.componentId() == chipOut1 && state.pinId() == 2)
                    .findFirst().orElseThrow().state();
        
                assert verifyOutput(state2, (i == 0) ? PinState.LOW : PinState.HIGH) == true;
                System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "tick: " + i + " pinNumber: " + 2));
        
            } catch (AssertionError e) {
                System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "tick: " + i + " pinNumber: " + 2 + ": " + e.getMessage()));
            }
        }
    }
}
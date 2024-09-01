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
        test_stationary_state_not_throw_unknown_state_exception_when_pin_has_unknown_but_is_not_connected();
        test_stationary_throws_unknown_state_exception_when_pin_state_is_unknown_and_is_connected();
        test_stationary_state_with_multiple_chips();
        test_stationary_state_with_no_state_change_on_chip();
        test_stationary_state_with_input_multiple_headers();
        test_stationary_state_with_output_multiple_headers();
        test_stationary_state_no_throw_exception_from_do_ukladu17(); // false
        test_stationary_state_complex_circuit();
        test_stationary_state_max_complex_circuit();
    }

    private void test_stationary_state_valid()
            throws UnknownChip, UnknownPin, UnknownComponent, ShortCircuitException, UnknownStateException {
        simulationManager.resetComponents();
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

    private void test_stationary_state_not_throw_unknown_state_exception_when_pin_has_unknown_but_is_not_connected()
            throws UnknownComponent, UnknownPin, ShortCircuitException, UnknownChip {
        simulationManager.resetComponents();
        int input = componentManager.createInputPinHeader(2);
        int component = componentManager.createChip(7408);

        Set<ComponentPinState> states = new HashSet<>();
        states.add(new ComponentPinState(input, 1, PinState.HIGH));
        states.add(new ComponentPinState(input, 2, PinState.UNKNOWN));

        componentManager.connect(input, 1, component, 1);

        try {
            simulationManager.stationaryState(states);
        } catch (UnknownStateException ex) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(),
                            "catch" + UnknownStateException.class.getSimpleName()));
        }
        try {
            assert verifyOutput(simulationManager.getComponents().get(component).getPinState(1), PinState.HIGH) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
    }

    private void test_stationary_throws_unknown_state_exception_when_pin_state_is_unknown_and_is_connected()
            throws UnknownChip, UnknownPin, UnknownComponent, ShortCircuitException {
        simulationManager.resetComponents();
        int input = componentManager.createInputPinHeader(2);
        int component = componentManager.createChip(7408);

        Set<ComponentPinState> states = new HashSet<>();
        states.add(new ComponentPinState(input, 1, PinState.HIGH));
        states.add(new ComponentPinState(input, 2, PinState.UNKNOWN));

        componentManager.connect(input, 1, component, 1);
        componentManager.connect(input, 2, component, 2);

        try {
            simulationManager.stationaryState(states);
        } catch (UnknownStateException ex) {
            System.out.println(
                    okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(),
                            "correctly catch" + UnknownStateException.class.getSimpleName() + ex.getMessage()));
        }
        try {
            assert verifyOutput(simulationManager.getComponents().get(component).getPinState(2),
                    PinState.UNKNOWN) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
    }

    private void test_stationary_state_with_multiple_chips()
            throws UnknownChip, UnknownPin, UnknownComponent, ShortCircuitException, UnknownStateException {
        simulationManager.resetComponents();
        int input = componentManager.createInputPinHeader(2);
        int componentOne = componentManager.createChip(7408);
        int componentTwo = componentManager.createChip(7400);
        int output = componentManager.createOutputPinHeader(2);

        Set<ComponentPinState> states = new HashSet<>();
        states.add(new ComponentPinState(input, 1, PinState.HIGH));
        states.add(new ComponentPinState(input, 2, PinState.HIGH));

        componentManager.connect(input, 1, componentOne, 1);
        componentManager.connect(input, 2, componentOne, 2);
        componentManager.connect(input, 1, componentTwo, 1);
        componentManager.connect(input, 2, componentTwo, 2);
        componentManager.connect(componentOne, 3, output, 1);
        componentManager.connect(componentTwo, 3, output, 2);

        try {
            simulationManager.stationaryState(states);
        } catch (UnknownStateException e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(),
                            "Problematic pin: " + e.pinState().componentId() + "." + e.pinState().pinId()));
        }

        try {
            assert verifyOutput(simulationManager.getComponents().get(output).getPinState(1), PinState.HIGH) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }

        try {
            assert verifyOutput(simulationManager.getComponents().get(output).getPinState(2), PinState.LOW) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
    }

    private void test_stationary_state_with_no_state_change_on_chip()
            throws UnknownComponent, UnknownPin, ShortCircuitException, UnknownChip {
        simulationManager.resetComponents();

        int input = componentManager.createInputPinHeader(2);
        int componentOne = componentManager.createChip(7408);
        int componentTwo = componentManager.createChip(7400);
        int output = componentManager.createOutputPinHeader(2);

        Set<ComponentPinState> states = new HashSet<>();
        states.add(new ComponentPinState(input, 1, PinState.HIGH));
        states.add(new ComponentPinState(input, 2, PinState.HIGH));

        componentManager.connect(input, 1, componentOne, 1);
        componentManager.connect(input, 2, componentOne, 2);
        componentManager.connect(input, 1, componentTwo, 1);
        componentManager.connect(input, 2, componentTwo, 2);
        componentManager.connect(componentOne, 3, output, 1);
        componentManager.connect(componentTwo, 3, output, 2);

        try {
            simulationManager.stationaryState(states);
        } catch (UnknownStateException e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(),
                            "Problematic pin: " + e.pinState().componentId() + "." + e.pinState().pinId()));
        }

        try {
            assert verifyOutput(simulationManager.getComponents().get(output).getPinState(1), PinState.HIGH) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }

        try {
            assert verifyOutput(simulationManager.getComponents().get(output).getPinState(2), PinState.LOW) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }

        testUnknown(input, 1, PinState.HIGH, this.getCurrentMethodName());
        testUnknown(input, 2, PinState.HIGH, this.getCurrentMethodName());
        testUnknown(componentOne, 1, PinState.HIGH, this.getCurrentMethodName());
        testUnknown(componentOne, 2, PinState.HIGH, this.getCurrentMethodName());
        testUnknown(componentOne, 3, PinState.HIGH, this.getCurrentMethodName());
        testUnknown(componentOne, 4, PinState.UNKNOWN, this.getCurrentMethodName());
        testUnknown(componentOne, 5, PinState.UNKNOWN, this.getCurrentMethodName());

        testUnknown(componentOne, 6, PinState.UNKNOWN, this.getCurrentMethodName());
        testUnknown(componentOne, 8, PinState.UNKNOWN, this.getCurrentMethodName());
        testUnknown(componentOne, 9, PinState.UNKNOWN, this.getCurrentMethodName());
        testUnknown(componentOne, 10, PinState.UNKNOWN, this.getCurrentMethodName());
        testUnknown(componentOne, 11, PinState.UNKNOWN, this.getCurrentMethodName());
        testUnknown(componentOne, 12, PinState.UNKNOWN, this.getCurrentMethodName());
        testUnknown(componentOne, 13, PinState.UNKNOWN, this.getCurrentMethodName());

        testUnknown(componentTwo, 1, PinState.HIGH, this.getCurrentMethodName());
        testUnknown(componentTwo, 2, PinState.HIGH, this.getCurrentMethodName());
        testUnknown(componentTwo, 3, PinState.LOW, this.getCurrentMethodName());
        testUnknown(componentTwo, 4, PinState.UNKNOWN, this.getCurrentMethodName());
        testUnknown(componentTwo, 5, PinState.UNKNOWN, this.getCurrentMethodName());

        testUnknown(componentTwo, 6, PinState.UNKNOWN, this.getCurrentMethodName());
        testUnknown(componentTwo, 8, PinState.UNKNOWN, this.getCurrentMethodName());
        testUnknown(componentTwo, 9, PinState.UNKNOWN, this.getCurrentMethodName());
        testUnknown(componentTwo, 10, PinState.UNKNOWN, this.getCurrentMethodName());
        testUnknown(componentTwo, 11, PinState.UNKNOWN, this.getCurrentMethodName());
        testUnknown(componentTwo, 12, PinState.UNKNOWN, this.getCurrentMethodName());
        testUnknown(componentTwo, 13, PinState.UNKNOWN, this.getCurrentMethodName());

        testUnknown(output, 1, PinState.HIGH, this.getCurrentMethodName());
        testUnknown(output, 2, PinState.LOW, this.getCurrentMethodName());
    }

    private void test_stationary_state_with_input_multiple_headers()
            throws UnknownComponent, UnknownPin, ShortCircuitException, UnknownChip {
        simulationManager.resetComponents();
        int chipId0 = componentManager.createInputPinHeader(2);
        int chipId1 = componentManager.createInputPinHeader(2);
        int chipId2 = componentManager.createChip(7400);
        int chipId3 = componentManager.createOutputPinHeader(1);

        Set<ComponentPinState> states = new HashSet<>();
        states.add(new ComponentPinState(chipId0, 1, PinState.HIGH));
        states.add(new ComponentPinState(chipId0, 2, PinState.HIGH));
        states.add(new ComponentPinState(chipId1, 1, PinState.LOW));
        states.add(new ComponentPinState(chipId1, 2, PinState.LOW));

        componentManager.connect(chipId0, 1, chipId2, 1);
        componentManager.connect(chipId1, 2, chipId2, 2);
        componentManager.connect(chipId2, 3, chipId3, 1);

        try {
            simulationManager.stationaryState(states);
        } catch (UnknownStateException e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(),
                            "Problematic pin: " + e.pinState().componentId() + "." + e.pinState().pinId()));
        }

        try {
            assert verifyOutput(simulationManager.getComponents().get(chipId3).getPinState(1), PinState.HIGH) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
    }

    private void test_stationary_state_with_output_multiple_headers()
            throws UnknownComponent, UnknownPin, ShortCircuitException, UnknownChip, UnknownStateException {
        simulationManager.resetComponents();
        int chipId1 = componentManager.createInputPinHeader(2);
        int chipId2 = componentManager.createChip(7400);
        int chipId3 = componentManager.createOutputPinHeader(1);
        int chipId4 = componentManager.createOutputPinHeader(1);

        Set<ComponentPinState> states = new HashSet<>();
        states.add(new ComponentPinState(chipId1, 1, PinState.HIGH));
        states.add(new ComponentPinState(chipId1, 2, PinState.LOW));

        componentManager.connect(chipId1, 1, chipId2, 1);
        componentManager.connect(chipId1, 2, chipId2, 2);
        componentManager.connect(chipId2, 3, chipId3, 1);
        componentManager.connect(chipId2, 3, chipId4, 1);

        simulationManager.stationaryState(states);

        try {
            assert verifyOutput(simulationManager.getComponents().get(chipId3).getPinState(1), PinState.HIGH) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }

        try {
            assert verifyOutput(simulationManager.getComponents().get(chipId4).getPinState(1), PinState.HIGH) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }

    }

    private void test_stationary_state_no_throw_exception_from_do_ukladu17()
            throws UnknownComponent, UnknownPin, ShortCircuitException, UnknownChip, UnknownStateException {
        simulationManager.resetComponents();
        int chipId0 = componentManager.createInputPinHeader(2);
		int chipId1 = componentManager.createChip(7431);
		int chipId2 = componentManager.createChip(7404);
		int chipId3 = componentManager.createOutputPinHeader(4);


		componentManager.connect(chipId0, 1, chipId1, 11);
		componentManager.connect(chipId0, 2, chipId1, 10);

		componentManager.connect(chipId1, 9, chipId3, 1);
		componentManager.connect(chipId1, 9, chipId2, 13);

		componentManager.connect(chipId2, 12, chipId2, 11);

		componentManager.connect(chipId2, 11, chipId3, 2);

		componentManager.connect(chipId2, 10, chipId3, 3);
		componentManager.connect(chipId2, 10, chipId1, 1);

		componentManager.connect(chipId1, 2, chipId3, 4);


		Set<ComponentPinState> states = new HashSet<>();
		states.add(new ComponentPinState(chipId0, 1, PinState.HIGH));
		states.add(new ComponentPinState(chipId0, 2, PinState.HIGH));

        try {
            simulationManager.stationaryState(states);
        } catch (UnknownStateException ex) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(),
                            "catch" + UnknownStateException.class.getSimpleName() + ex.getMessage()));
        }

        try {
            assert verifyOutput(simulationManager.getComponents().get(chipId3).getPinState(1), PinState.LOW) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }

        try {
            assert verifyOutput(simulationManager.getComponents().get(chipId3).getPinState(2), PinState.HIGH) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }

        try {
            assert verifyOutput(simulationManager.getComponents().get(chipId3).getPinState(3), PinState.LOW) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }

        try {
            assert verifyOutput(simulationManager.getComponents().get(chipId3).getPinState(4), PinState.HIGH) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
    }

    private void test_stationary_state_complex_circuit()
            throws UnknownComponent, UnknownPin, ShortCircuitException, UnknownChip, UnknownStateException {
        int chipIn1 = componentManager.createInputPinHeader(3);
        int chip7400 = componentManager.createChip(7400);
        int chip7402 = componentManager.createChip(7402);
        int chip7404 = componentManager.createChip(7404);
        int chipOut1 = componentManager.createOutputPinHeader(2);

        componentManager.connect(chipIn1, 1, chip7400, 1);
        componentManager.connect(chipIn1, 2, chip7400, 2);
        componentManager.connect(chipIn1, 3, chip7402, 9);

        componentManager.connect(chip7400, 3, chip7402, 8);
        componentManager.connect(chip7400, 3, chipOut1, 1);

        componentManager.connect(chip7402, 10, chip7404, 3);

        componentManager.connect(chip7404, 4, chipOut1, 2);

        Set<ComponentPinState> states = new HashSet<>();
        states.add(new ComponentPinState(chipIn1, 1, PinState.HIGH));
        states.add(new ComponentPinState(chipIn1, 2, PinState.LOW));
        states.add(new ComponentPinState(chipIn1, 3, PinState.HIGH));

        try {
            simulationManager.stationaryState(states);
        } catch (UnknownStateException ex) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(),
                            "catch" + UnknownStateException.class.getSimpleName() + ex.getMessage()));
        }

        try {
            assert verifyOutput(simulationManager.getComponents().get(chipOut1).getPinState(1), PinState.HIGH) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }

        try {
            assert verifyOutput(simulationManager.getComponents().get(chipOut1).getPinState(2), PinState.HIGH) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
    }

    void test_stationary_state_max_complex_circuit()
            throws UnknownChip, UnknownStateException, UnknownPin, ShortCircuitException, UnknownComponent {
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

        try {
            simulationManager.stationaryState(states);
        } catch (UnknownStateException ex) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(),
                            "catch" + UnknownStateException.class.getSimpleName() + ex.getMessage()));
        }

        try {
            assert verifyOutput(simulationManager.getComponents().get(chipOut0).getPinState(1), PinState.HIGH) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
        try {
            assert verifyOutput(simulationManager.getComponents().get(chipOut1).getPinState(1), PinState.HIGH) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
        try {
            assert verifyOutput(simulationManager.getComponents().get(chipOut1).getPinState(2), PinState.LOW) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
        // Assertions.assertEquals(PinState.HIGH, simulation.getChips().get(chipOut0).getPinMap().get(1).getPinState());
        // Assertions.assertEquals(PinState.HIGH, simulation.getChips().get(chipOut1).getPinMap().get(1).getPinState());
        // Assertions.assertEquals(PinState.LOW, simulation.getChips().get(chipOut1).getPinMap().get(2).getPinState());
    }

    private void testUnknown(int idComponent, int pinNumber, PinState state, String methodName) throws UnknownPin {
        String msg = methodName + " compId/PinNumber" + ": " + idComponent + "/" + pinNumber;
        try {
            assert verifyOutput(simulationManager.getComponents().get(idComponent).getPinState(pinNumber),
                    state) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), msg));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), msg));
        }
    }

    @Override
    protected void testComponent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testComponent'");
    }
}

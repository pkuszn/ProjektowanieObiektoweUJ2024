package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.builders.IC74HC10Builder;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.components.IC74HC10;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Test_IC74HC10 extends TestBase {
    private final IC74HC10 component;

    public Test_IC74HC10() {
        super();
        this.component = (IC74HC10) this.director.make(new IC74HC10Builder());
    }
    @Override
    protected void testComponent() {
        test_all_gates_should_return_false();
        test_all_gates_should_return_true();
        test_all_gates_should_return_low_becauase_of_two_falses();
        test_all_gates_should_return_low_because_of_two_trues();
        test_all_gates_should_return_low_because_of_two_trues_another_pair_of_pins();
        test_all_gates_should_return_low_because_of_two_falses_another_pair_of_pins();
    }
    /**
     * All gates should return true
     */
    private void test_all_gates_should_return_true() {
        resetPins(this.component);
        PinState targetState = PinState.HIGH;

        Map<Integer, ComponentPin> pins = this.component.getPins();

        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                pin.setState(PinState.LOW);
            }
        }

        this.component.applyCommand();

        List<PinState> pinsOut = new ArrayList<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.add(pin.getState());
            }
        }

        try {
            assert verifyAllEqualToSpecificState(pinsOut, targetState) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));
        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
    }

    /**
     * All gates should return false;
     */
    private void test_all_gates_should_return_false() {
        resetPins(this.component);
        PinState targetState = PinState.LOW;

        Map<Integer, ComponentPin> pins = this.component.getPins();

        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                pin.setState(PinState.HIGH);
            }
        }

        this.component.applyCommand();

        List<PinState> pinsOut = new ArrayList<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.add(pin.getState());
            }
        }

        try {
            assert verifyAllEqualToSpecificState(pinsOut, targetState) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));
        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
    }

    /**
     * Więcej pinów ma stan niski niż większy w bramce logicznej
     */
    private void test_all_gates_should_return_low_becauase_of_two_falses() {
        resetPins(this.component);
        PinState targetState = PinState.HIGH;

        Map<Integer, ComponentPin> pins = this.component.getPins();
        List<Integer> falsePins = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 9, 10));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (falsePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.LOW);
                } else {
                    pin.setState(PinState.HIGH);
                }
            }
        }

         this.component.applyCommand();

        List<PinState> pinsOut = new ArrayList<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.add(pin.getState());
            }
        }

        try {
            assert verifyAllEqualToSpecificState(pinsOut, targetState) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));
        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
    }

    /**
     * Nie wszystkie piny maja stan niski
     */
    private void test_all_gates_should_return_low_because_of_two_trues() {
        resetPins(this.component);
        PinState targetState = PinState.HIGH;

        Map<Integer, ComponentPin> pins = this.component.getPins();
        List<Integer> falsePins = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 9, 10));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (falsePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();

        List<PinState> pinsOut = new ArrayList<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.add(pin.getState());
            }
        }

        try {
            assert verifyAllEqualToSpecificState(pinsOut, targetState) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));
        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
    }

        /**
     * Więcej pinów ma stan niski niż większy w bramce logicznej
     */
    private void test_all_gates_should_return_low_because_of_two_falses_another_pair_of_pins() {
        resetPins(this.component);
        PinState targetState = PinState.HIGH;

        Map<Integer, ComponentPin> pins = this.component.getPins();
        List<Integer> falsePins = new ArrayList<>(Arrays.asList(13, 2, 3, 5, 11, 10));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (falsePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.LOW);
                } else {
                    pin.setState(PinState.HIGH);
                }
            }
        }

         this.component.applyCommand();

        List<PinState> pinsOut = new ArrayList<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.add(pin.getState());
            }
        }

        try {
            assert verifyAllEqualToSpecificState(pinsOut, targetState) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));
        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
    }

    /**
     * Nie wszystkie piny maja stan niski
     */
    private void test_all_gates_should_return_low_because_of_two_trues_another_pair_of_pins() {
        resetPins(this.component);
        PinState targetState = PinState.HIGH;

        Map<Integer, ComponentPin> pins = this.component.getPins();
        List<Integer> falsePins = new ArrayList<>(Arrays.asList(13, 2, 3, 5, 11, 10));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (falsePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();

        List<PinState> pinsOut = new ArrayList<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.add(pin.getState());
            }
        }

        try {
            assert verifyAllEqualToSpecificState(pinsOut, targetState) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));
        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
    }
}

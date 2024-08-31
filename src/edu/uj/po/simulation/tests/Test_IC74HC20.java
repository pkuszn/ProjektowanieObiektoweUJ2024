package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.builders.IC74HC20Builder;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.components.IC74HC20;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Test_IC74HC20 extends TestBase {
    private final IC74HC20 component;

    public Test_IC74HC20() {
        super();
        this.component = (IC74HC20) this.director.make(new IC74HC20Builder());
    }

    @Override
    protected void testComponent() {
        test_all_gates_should_return_false();
        test_all_gates_should_return_true();
        test_all_gates_should_return_high_because_of_one_false();
        test_all_gates_should_return_high_because_of_one_true();
        test_all_gates_should_return_high_because_of_pop_has_two_falses();
        test_all_gates_should_return_high_because_of_pop_has_two_trues();
        test_all_gates_should_return_high_because_of_pop_has_three_trues();
        test_all_gates_should_return_high_because_of_pop_has_three_falses();
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
     * Chociaż jeden pin ma stan niski w bramce
     */
    private void test_all_gates_should_return_high_because_of_one_false() {
        resetPins(this.component);
        PinState targetState = PinState.HIGH;

        Map<Integer, ComponentPin> pins = this.component.getPins();
        List<Integer> falsePins = new ArrayList<>(Arrays.asList(1, 9));
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
     * Jeden pin ma stan wysoki w bramce
     */
    private void test_all_gates_should_return_high_because_of_one_true() {
        resetPins(this.component);
        PinState targetState = PinState.HIGH;

        Map<Integer, ComponentPin> pins = this.component.getPins();
        List<Integer> falsePins = new ArrayList<>(Arrays.asList(1, 9));
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
     * Dwa piny mają stan wysoki w bramce
     */
    private void test_all_gates_should_return_high_because_of_pop_has_two_trues() {
        resetPins(this.component);
        PinState targetState = PinState.HIGH;

        Map<Integer, ComponentPin> pins = this.component.getPins();
        List<Integer> falsePins = new ArrayList<>(Arrays.asList(1, 2, 10, 9));
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
     * Dwa piny mają stan niski w bramce
     */
    private void test_all_gates_should_return_high_because_of_pop_has_two_falses() {
        resetPins(this.component);
        PinState targetState = PinState.HIGH;

        Map<Integer, ComponentPin> pins = this.component.getPins();
        List<Integer> falsePins = new ArrayList<>(Arrays.asList(1, 2, 10, 9));
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
     * Trzy piny mają stan wysoki w bramce
     */
    private void test_all_gates_should_return_high_because_of_pop_has_three_trues() {
        resetPins(this.component);
        PinState targetState = PinState.HIGH;

        Map<Integer, ComponentPin> pins = this.component.getPins();
        List<Integer> falsePins = new ArrayList<>(Arrays.asList(1, 2, 4, 10, 9, 12));
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
     * Trzy piny mają stan niski w bramce
     */
    private void test_all_gates_should_return_high_because_of_pop_has_three_falses() {
        resetPins(this.component);
        PinState targetState = PinState.HIGH;

        Map<Integer, ComponentPin> pins = this.component.getPins();
        List<Integer> falsePins = new ArrayList<>(Arrays.asList(1, 2, 4, 10, 9, 12));
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

}

package edu.uj.po.simulation.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.uj.po.simulation.builders.IC7482Builder;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.components.IC7482;

public class Test_IC7482 extends TestBase {
    private final IC7482 component;

    public Test_IC7482() {
        super();
        this.component = (IC7482) this.director.make(new IC7482Builder());
    }

    @Override
    protected void testComponent() {
        test_number_one_sumator_carry_zero();
        test_number_one_sumator_carry_one();
        test_number_two_sumator_carry_zero();
        test_number_two_sumator_carry_one();
        test_number_three_sumator_carry_zero();
        test_number_three_sumator_carry_one();
        test_number_four_sumator_carry_zero();
        test_number_four_sumator_carry_one();
        test_number_five_sumator_carry_zero();
        test_number_five_sumator_carry_one();
        test_number_six_sumator_carry_zero();
        test_number_six_sumator_carry_one();
        test_number_seven_sumator_carry_zero();
        test_number_seven_sumator_carry_one();
        test_number_eight_sumator_carry_zero();
        test_number_eight_sumator_carry_one();
        test_number_nine_sumator_carry_zero();
        test_number_nine_sumator_carry_one();
        test_number_ten_sumator_carry_zero();
        test_number_ten_sumator_carry_one();
        test_number_eleven_sumator_carry_zero();
        test_number_eleven_sumator_carry_one();
        test_number_twelve_sumator_carry_zero();
        test_number_twelve_sumator_carry_one();
    }

    private void test_number_one_sumator_carry_zero() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList());
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_one_sumator_carry_one() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(5));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.HIGH, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_two_sumator_carry_zero() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(2));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.HIGH, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); // E_2 suma2
    }


    private void test_number_two_sumator_carry_one() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(2, 5));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.HIGH, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_three_sumator_carry_zero() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(3));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.HIGH, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_three_sumator_carry_one() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(3, 5));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.HIGH, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_four_sumator_carry_zero() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(2, 3));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.HIGH, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_four_sumator_carry_one() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(2, 3, 5));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.HIGH, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.HIGH, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_five_sumator_carry_zero() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(14));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.HIGH, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_five_sumator_carry_one() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(14, 5));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.HIGH, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.HIGH, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_six_sumator_carry_zero() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(14, 2));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.HIGH, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.HIGH, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_six_sumator_carry_one() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(14, 5, 2));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.HIGH, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_seven_sumator_carry_zero() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(14, 3));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.HIGH, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.HIGH, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_seven_sumator_carry_one() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(14, 5, 3));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.HIGH, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_eight_sumator_carry_zero() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(14, 3, 2));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.HIGH, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_eight_sumator_carry_one() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(14, 5, 3, 2));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.HIGH, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.HIGH, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); // E_2 suma2
    }

    //TODO: This test doenst work
    private void test_number_nine_sumator_carry_zero() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(14, 13));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.HIGH, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_nine_sumator_carry_one() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(14, 13, 5));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.HIGH, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.HIGH, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_ten_sumator_carry_zero() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(14, 13, 2));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.HIGH, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.HIGH, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_ten_sumator_carry_one() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(14, 13, 2, 5));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.HIGH, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.HIGH, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_eleven_sumator_carry_zero() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(14, 13, 3));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.HIGH, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.HIGH, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_eleven_sumator_carry_one() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(14, 13, 3, 5));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.HIGH, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.HIGH, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_twelve_sumator_carry_zero() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(14, 13, 3, 2));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.HIGH, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.HIGH, getCurrentMethodName()); // E_2 suma2
    }

    private void test_number_twelve_sumator_carry_one() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(14, 13, 3, 2, 5));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.HIGH, getCurrentMethodName()); // E_1 Suma1
        checkPinState(pinsOut, 10, PinState.HIGH, getCurrentMethodName()); //c2
        checkPinState(pinsOut, 12, PinState.HIGH, getCurrentMethodName()); // E_2 suma2
    }
}

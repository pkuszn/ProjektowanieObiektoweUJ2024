package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.builders.IC74LS42Builder;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.components.IC74LS42;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test_IC74LS42 extends TestBase {
    private final IC74LS42 component;

    public Test_IC74LS42() {
        super();
        this.component = (IC74LS42) this.director.make(new IC74LS42Builder());
    }

    @Override
    protected void testComponent() {
        test_number_zero_bcd_to_one_ten();
        test_number_one_bcd_to_one_ten();
        test_number_two_bcd_to_one_ten();
        test_number_three_bcd_to_one_ten();
        test_number_four_bcd_to_one_ten();
        test_number_five_bcd_to_one_ten();
        test_number_seven_bcd_to_one_ten();
        test_number_eight_bcd_to_one_ten();
        test_number_nine_bcd_to_one_ten();
        test_number_ten_bcd_to_one_ten();
        test_number_eleven_bcd_to_one_ten();
        test_number_twelve_bcd_to_one_ten();
        test_number_thirteen_bcd_to_one_ten();
        test_number_fourteen_bcd_to_one_ten();
        test_number_fiveteen_bcd_to_one_ten();
        test_number_sixteen_bcd_to_one_ten();
    }

    /**
     * output 2 true
     */
    private void test_number_zero_bcd_to_one_ten() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                pin.setState(PinState.LOW);
            }
        }

        this.component.applyCommand();
        Map<Integer, PinState> pinsOut = new HashMap<>();
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                pinsOut.put(pin.getPinNumber(), pin.getState());
            }
        }

        checkPinState(pinsOut, 1, PinState.HIGH, getCurrentMethodName());
        checkPinState(pinsOut, 2, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut,3, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 4, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 5, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 6, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName());
    }

    /**
     * output 2 true
     */
    private void test_number_one_bcd_to_one_ten() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(15));
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

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 2, PinState.HIGH, getCurrentMethodName());
        checkPinState(pinsOut,3, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 4, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 5, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 6, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName());
    }

    /**
     * output 3 true
     */
    private void test_number_two_bcd_to_one_ten() {
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

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 2, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut,3, PinState.HIGH, getCurrentMethodName());
        checkPinState(pinsOut, 4, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 5, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 6, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName());
    }


    /**
     * output 4 true
     */
    private void test_number_three_bcd_to_one_ten() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(15, 14));
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

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 2, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut,3, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 4, PinState.HIGH, getCurrentMethodName());
        checkPinState(pinsOut, 5, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 6, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName());
    }

    
    /**
     * output 5 true
     */
    private void test_number_four_bcd_to_one_ten() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(13));
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

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 2, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut,3, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 4, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 5, PinState.HIGH, getCurrentMethodName());
        checkPinState(pinsOut, 6, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName());
    }

     /**
     * output 6 true
     */
    private void test_number_five_bcd_to_one_ten() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(13, 15));
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

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 2, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut,3, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 4, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 5, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 6, PinState.HIGH, getCurrentMethodName());
        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName());
    }

    /**
     * output 7 true
     */
    private void test_number_seven_bcd_to_one_ten() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(13, 14));
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

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 2, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut,3, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 4, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 5, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 6, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 7, PinState.HIGH, getCurrentMethodName());
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName());
    }

    /**
     * output 9 true
     */
    private void test_number_eight_bcd_to_one_ten() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(13, 14, 15));
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

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 2, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut,3, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 4, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 5, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 6, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 9, PinState.HIGH, getCurrentMethodName());
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName());
    }

    /**
     * output 10 true
     */
    private void test_number_nine_bcd_to_one_ten() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(12));
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

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 2, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut,3, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 4, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 5, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 6, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 10, PinState.HIGH, getCurrentMethodName());
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName());
    }

    /**
     * output 11 true
     */
    private void test_number_ten_bcd_to_one_ten() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(12, 15));
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

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 2, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut,3, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 4, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 5, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 6, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 11, PinState.HIGH, getCurrentMethodName());
    }

    /**
     * all false
     */
    private void test_number_eleven_bcd_to_one_ten() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(12, 14));
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

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 2, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut,3, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 4, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 5, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 6, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName());
    }

    /**
     * all false
     */
    private void test_number_twelve_bcd_to_one_ten() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(12, 14, 15));
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

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 2, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut,3, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 4, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 5, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 6, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName());
    }

    /**
     * all false
     */
    private void test_number_thirteen_bcd_to_one_ten() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(12, 13));
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

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 2, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut,3, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 4, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 5, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 6, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName());
    }

    /**
     * all false
     */
    private void test_number_fourteen_bcd_to_one_ten() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(12, 13, 15));
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

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 2, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut,3, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 4, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 5, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 6, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName());
    }

    /**
     * all false
     */
    private void test_number_fiveteen_bcd_to_one_ten() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(12, 13, 14));
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

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 2, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut,3, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 4, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 5, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 6, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName());
    }

    
    /**
     * all false
     */
    private void test_number_sixteen_bcd_to_one_ten() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(12, 13, 14, 15));
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

        checkPinState(pinsOut, 1, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 2, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut,3, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 4, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 5, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 6, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName());
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName());
    }
}

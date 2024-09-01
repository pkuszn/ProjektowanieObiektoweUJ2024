package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.UnknownChip;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.components.IC74138;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test_IC74138 extends TestBase {
    private final IC74138 component;

    public Test_IC74138() throws UnknownChip {
        super();
        this.component = (IC74138) this.director.orderComponentBuild(74138);
    }

    @Override
    protected void testComponent() {
        test_number_one_demultiplexer();
        test_number_two_demultiplexer();
        test_number_three_demultiplexer();
        test_number_four_demultiplexer();
        test_number_five_demultiplexer();
        test_number_six_demultiplexer();
        test_number_seven_demultiplexer();
        test_number_eight_demultiplexer();
        test_number_nine_demultiplexer();
        test_number_ten_demultiplexer();
    }

    private void test_number_one_demultiplexer() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(4, 5));
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

        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName()); //y7
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName()); //y6
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //y5
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName()); //y4
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); //y3
        checkPinState(pinsOut, 13, PinState.LOW, getCurrentMethodName()); //y2
        checkPinState(pinsOut, 14, PinState.LOW, getCurrentMethodName()); //y1
        checkPinState(pinsOut, 15, PinState.LOW, getCurrentMethodName()); //y0
    }

    
    private void test_number_two_demultiplexer() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(6));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (truePins.contains(pin.getPinNumber())) {
                    pin.setState(PinState.LOW);
                } else {
                    pin.setState(PinState.HIGH);
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

        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName()); //y7
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName()); //y6
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //y5
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName()); //y4
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); //y3
        checkPinState(pinsOut, 13, PinState.LOW, getCurrentMethodName()); //y2
        checkPinState(pinsOut, 14, PinState.LOW, getCurrentMethodName()); //y1
        checkPinState(pinsOut, 15, PinState.LOW, getCurrentMethodName()); //y0
    }

    private void test_number_three_demultiplexer() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(6));
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

        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName()); //y7
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName()); //y6
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //y5
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName()); //y4
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); //y3
        checkPinState(pinsOut, 13, PinState.LOW, getCurrentMethodName()); //y2
        checkPinState(pinsOut, 14, PinState.LOW, getCurrentMethodName()); //y1
        checkPinState(pinsOut, 15, PinState.HIGH, getCurrentMethodName()); //y0
    }

    private void test_number_four_demultiplexer() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(6, 1));
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

        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName()); //y7
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName()); //y6
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //y5
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName()); //y4
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); //y3
        checkPinState(pinsOut, 13, PinState.LOW, getCurrentMethodName()); //y2
        checkPinState(pinsOut, 14, PinState.HIGH, getCurrentMethodName()); //y1
        checkPinState(pinsOut, 15, PinState.LOW, getCurrentMethodName()); //y0
    }

    private void test_number_five_demultiplexer() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(6, 2));
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

        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName()); //y7
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName()); //y6
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //y5
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName()); //y4
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); //y3
        checkPinState(pinsOut, 13, PinState.HIGH, getCurrentMethodName()); //y2
        checkPinState(pinsOut, 14, PinState.LOW, getCurrentMethodName()); //y1
        checkPinState(pinsOut, 15, PinState.LOW, getCurrentMethodName()); //y0
    }

    private void test_number_six_demultiplexer() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(6, 2, 1));
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

        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName()); //y7
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName()); //y6
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //y5
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName()); //y4
        checkPinState(pinsOut, 12, PinState.HIGH, getCurrentMethodName()); //y3
        checkPinState(pinsOut, 13, PinState.LOW, getCurrentMethodName()); //y2
        checkPinState(pinsOut, 14, PinState.LOW, getCurrentMethodName()); //y1
        checkPinState(pinsOut, 15, PinState.LOW, getCurrentMethodName()); //y0
    }

    private void test_number_seven_demultiplexer() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(6, 3));
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

        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName()); //y7
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName()); //y6
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //y5
        checkPinState(pinsOut, 11, PinState.HIGH, getCurrentMethodName()); //y4
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); //y3
        checkPinState(pinsOut, 13, PinState.LOW, getCurrentMethodName()); //y2
        checkPinState(pinsOut, 14, PinState.LOW, getCurrentMethodName()); //y1
        checkPinState(pinsOut, 15, PinState.LOW, getCurrentMethodName()); //y0
    }

    private void test_number_eight_demultiplexer() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(6, 3, 1));
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

        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName()); //y7
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName()); //y6
        checkPinState(pinsOut, 10, PinState.HIGH, getCurrentMethodName()); //y5
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName()); //y4
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); //y3
        checkPinState(pinsOut, 13, PinState.LOW, getCurrentMethodName()); //y2
        checkPinState(pinsOut, 14, PinState.LOW, getCurrentMethodName()); //y1
        checkPinState(pinsOut, 15, PinState.LOW, getCurrentMethodName()); //y0
    }

    private void test_number_nine_demultiplexer() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(6, 3, 2));
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

        checkPinState(pinsOut, 7, PinState.LOW, getCurrentMethodName()); //y7
        checkPinState(pinsOut, 9, PinState.HIGH, getCurrentMethodName()); //y6
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //y5
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName()); //y4
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); //y3
        checkPinState(pinsOut, 13, PinState.LOW, getCurrentMethodName()); //y2
        checkPinState(pinsOut, 14, PinState.LOW, getCurrentMethodName()); //y1
        checkPinState(pinsOut, 15, PinState.LOW, getCurrentMethodName()); //y0
    }

    private void test_number_ten_demultiplexer() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(6, 3, 2, 1));
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

        checkPinState(pinsOut, 7, PinState.HIGH, getCurrentMethodName()); //y7
        checkPinState(pinsOut, 9, PinState.LOW, getCurrentMethodName()); //y6
        checkPinState(pinsOut, 10, PinState.LOW, getCurrentMethodName()); //y5
        checkPinState(pinsOut, 11, PinState.LOW, getCurrentMethodName()); //y4
        checkPinState(pinsOut, 12, PinState.LOW, getCurrentMethodName()); //y3
        checkPinState(pinsOut, 13, PinState.LOW, getCurrentMethodName()); //y2
        checkPinState(pinsOut, 14, PinState.LOW, getCurrentMethodName()); //y1
        checkPinState(pinsOut, 15, PinState.LOW, getCurrentMethodName()); //y0
    }
}

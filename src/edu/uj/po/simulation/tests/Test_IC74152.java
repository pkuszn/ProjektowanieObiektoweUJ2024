package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.UnknownChip;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.components.IC74152;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test_IC74152 extends TestBase {
    private final IC74152 component;

    public Test_IC74152() throws UnknownChip {
        super();
        this.component = (IC74152) this.director.orderComponentBuild(74152);
    }

    @Override
    protected void testComponent() {
        test_number_one_multiplexer();
        test_number_two_multiplexer();
        test_number_three_multiplexer();
        test_number_four_multiplexer();
        test_number_five_multiplexer();
        test_number_six_multiplexer();
        test_number_seven_multiplexer();
        test_number_eight_multiplexer();
    }

    private void test_number_one_multiplexer() {
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
        PinState assignedPin = negate(pinsOut.get(5));
        checkPinState(pinsOut, 6, assignedPin, getCurrentMethodName()); // w
    }

    private void test_number_two_multiplexer() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(10));
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
        PinState assignedPin = negate(pinsOut.get(4));
        checkPinState(pinsOut, 6, assignedPin, getCurrentMethodName()); // w
    }

    private void test_number_three_multiplexer() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(9));
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
        PinState assignedPin = negate(pinsOut.get(3));
        checkPinState(pinsOut, 6, assignedPin, getCurrentMethodName()); // w
    }
    
    private void test_number_four_multiplexer() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(10, 9));
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
        PinState assignedPin = negate(pinsOut.get(2));
        checkPinState(pinsOut, 6, assignedPin, getCurrentMethodName()); // w
    }

    private void test_number_five_multiplexer() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(8));
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
        PinState assignedPin = negate(pinsOut.get(1));
        checkPinState(pinsOut, 6, assignedPin, getCurrentMethodName()); // w
    }

    private void test_number_six_multiplexer() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(10, 8));
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
        PinState assignedPin = negate(pinsOut.get(13));
        checkPinState(pinsOut, 6, assignedPin, getCurrentMethodName()); // w
    }

    private void test_number_seven_multiplexer() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(8, 9));
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
        PinState assignedPin = negate(pinsOut.get(12));
        checkPinState(pinsOut, 6, assignedPin, getCurrentMethodName()); // w
    }
    
    private void test_number_eight_multiplexer() {
        resetPins(this.component);
        Map<Integer, ComponentPin> pins = this.component.getPins();

        List<Integer> truePins = new ArrayList<>(Arrays.asList(10, 9, 8));
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
        PinState assignedPin = negate(pinsOut.get(11));
        checkPinState(pinsOut, 6, assignedPin, getCurrentMethodName()); // w
    }


    private static PinState negate(PinState state) {
        if (state == null) {
            return PinState.UNKNOWN;
        }

        return switch (state) {
            case HIGH -> PinState.LOW;
            case LOW -> PinState.HIGH;
            default -> PinState.UNKNOWN;
        };
    }

}

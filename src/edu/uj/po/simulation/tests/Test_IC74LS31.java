package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.UnknownChip;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.components.IC74LS31;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Test_IC74LS31 extends TestBase {
    private final IC74LS31 component;

    public Test_IC74LS31() throws UnknownChip {
        super();
        this.component = (IC74LS31) this.director.orderComponentBuild(7431);
    }

    @Override
    public void testComponent() {
        test_all_gates_have_true_inputs();
        test_all_gates_have_false_inputs();
        test_all_gates_have_nand_test();
        test_all_gates_have_nand_test_another_pair();
    }

    /**
     * All gates have true inputs
     */
    private void test_all_gates_have_true_inputs() {
        resetPins(this.component);

        Map<Integer, ComponentPin> pins = this.component.getPins();

        PinState notGatesOutput = PinState.LOW;
        List<Integer> notGates = new ArrayList<>(Arrays.asList(1, 15));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (notGates.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                }
            }
        }
        PinState gatesOutput = PinState.HIGH;
        List<Integer> gates = new ArrayList<>(Arrays.asList(3, 13));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (gates.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                }
            }
        }

        PinState nandGatesOutput = PinState.LOW;
        List<Integer> nandGates = new ArrayList<>(Arrays.asList(5, 6, 10, 11));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (nandGates.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                }
            }
        }

        this.component.applyCommand();

        List<PinState> notPinsOut = new ArrayList<>();
        List<Integer> notOuts = new ArrayList<>(Arrays.asList(2, 14));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                if (notOuts.contains(pin.getPinNumber())) {
                    notPinsOut.add(pin.getState());
                }
            }
        }

        List<PinState> pinsOut = new ArrayList<>();
        List<Integer> outs = new ArrayList<>(Arrays.asList(4, 12));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                if (outs.contains(pin.getPinNumber())) {
                    pinsOut.add(pin.getState());
                }
            }
        }

        List<PinState> nandPinsOut = new ArrayList<>();
        List<Integer> nandOuts = new ArrayList<>(Arrays.asList(7, 9));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                if (nandOuts.contains(pin.getPinNumber())) {
                    nandPinsOut.add(pin.getState());
                }
            }
        }

        try {
            assert verifyAllEqualToSpecificState(notPinsOut, nandGatesOutput) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "NOT"));
        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "NOT", e.getMessage()));
        }

        try {
            assert verifyAllEqualToSpecificState(pinsOut, gatesOutput) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "GATE"));
        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "GATE",
                            e.getMessage()));
        }

        try {
            assert verifyAllEqualToSpecificState(nandPinsOut, notGatesOutput) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "NAND"));
        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "NAND",
                            e.getMessage()));
        }
    }

    /**
     * All gates have false inputs
     */
    private void test_all_gates_have_false_inputs() {
        resetPins(this.component);

        Map<Integer, ComponentPin> pins = this.component.getPins();

        PinState notGatesOutput = PinState.HIGH;
        List<Integer> notGates = new ArrayList<>(Arrays.asList(1, 15));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (notGates.contains(pin.getPinNumber())) {
                    pin.setState(PinState.LOW);
                }
            }
        }
        PinState gatesOutput = PinState.LOW;
        List<Integer> gates = new ArrayList<>(Arrays.asList(3, 13));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (gates.contains(pin.getPinNumber())) {
                    pin.setState(PinState.LOW);
                }
            }
        }

        PinState nandGatesOutput = PinState.HIGH;
        List<Integer> nandGates = new ArrayList<>(Arrays.asList(5, 6, 10, 11));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (nandGates.contains(pin.getPinNumber())) {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();

        List<PinState> notPinsOut = new ArrayList<>();
        List<Integer> notOuts = new ArrayList<>(Arrays.asList(2, 14));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                if (notOuts.contains(pin.getPinNumber())) {
                    notPinsOut.add(pin.getState());
                }
            }
        }

        List<PinState> pinsOut = new ArrayList<>();
        List<Integer> outs = new ArrayList<>(Arrays.asList(4, 12));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                if (outs.contains(pin.getPinNumber())) {
                    pinsOut.add(pin.getState());
                }
            }
        }

        List<PinState> nandPinsOut = new ArrayList<>();
        List<Integer> nandOuts = new ArrayList<>(Arrays.asList(7, 9));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                if (nandOuts.contains(pin.getPinNumber())) {
                    nandPinsOut.add(pin.getState());
                }
            }
        }

        try {
            assert verifyAllEqualToSpecificState(notPinsOut, nandGatesOutput) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), "NOT", this.getCurrentMethodName()));
        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "NOT", e.getMessage()));
        }

        try {
            assert verifyAllEqualToSpecificState(pinsOut, gatesOutput) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), "GATE", this.getCurrentMethodName()));
        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "GATE",
                            e.getMessage()));
        }

        try {
            assert verifyAllEqualToSpecificState(nandPinsOut, notGatesOutput) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), "NAND", this.getCurrentMethodName()));
        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "NAND",
                            e.getMessage()));
        }
    }

    /**
     * All gates have false inputs
     */
    private void test_all_gates_have_nand_test() {
        resetPins(this.component);

        Map<Integer, ComponentPin> pins = this.component.getPins();

        PinState nandGatesOutput = PinState.HIGH;
        List<Integer> nandGates = new ArrayList<>(Arrays.asList(5, 10));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (nandGates.contains(pin.getPinNumber())) {
                    pin.setState(PinState.LOW);
                } else {
                    pin.setState(PinState.HIGH);
                }
            }
        }

        this.component.applyCommand();

        List<PinState> nandPinsOut = new ArrayList<>();
        List<Integer> nandOuts = new ArrayList<>(Arrays.asList(7, 9));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                if (nandOuts.contains(pin.getPinNumber())) {
                    nandPinsOut.add(pin.getState());
                }
            }
        }

        try {
            assert verifyAllEqualToSpecificState(nandPinsOut, nandGatesOutput) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), "NAND", this.getCurrentMethodName()));
        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "NAND",
                            e.getMessage()));
        }
    }

      /**
     * All gates have false inputs
     */
    private void test_all_gates_have_nand_test_another_pair() {
        resetPins(this.component);

        Map<Integer, ComponentPin> pins = this.component.getPins();

        PinState nandGatesOutput = PinState.HIGH;
        List<Integer> nandGates = new ArrayList<>(Arrays.asList(6, 11));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.IN) {
                if (nandGates.contains(pin.getPinNumber())) {
                    pin.setState(PinState.HIGH);
                } else {
                    pin.setState(PinState.LOW);
                }
            }
        }

        this.component.applyCommand();

        List<PinState> nandPinsOut = new ArrayList<>();
        List<Integer> nandOuts = new ArrayList<>(Arrays.asList(7, 9));
        for (ComponentPin pin : pins.values()) {
            if (pin.getPinType() == PinType.OUT) {
                if (nandOuts.contains(pin.getPinNumber())) {
                    nandPinsOut.add(pin.getState());
                }
            }
        }

        try {
            assert verifyAllEqualToSpecificState(nandPinsOut, nandGatesOutput) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), "NAND", this.getCurrentMethodName()));
        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), "NAND",
                            e.getMessage()));
        }
    }
}

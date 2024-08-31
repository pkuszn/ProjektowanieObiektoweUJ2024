package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.builders.IC74ALS34Builder;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.components.IC74ALS34;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test_IC74ALS34 extends TestBase {
    private final IC74ALS34 component;

    public Test_IC74ALS34() {
        super();
        this.component = (IC74ALS34) this.director.make(new IC74ALS34Builder());
    }

    @Override
    public void testComponent() {
        test_all_gates_should_return_true();
        test_all_gates_should_return_false();
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
     * All gates should return false;
     */
    private void test_all_gates_should_return_false() {
        resetPins(this.component);
        PinState targetState = PinState.LOW;

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
}

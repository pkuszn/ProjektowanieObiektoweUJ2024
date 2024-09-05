package edu.uj.po.simulation.commands;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentCommand;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import java.util.HashMap;

public class IC7482Command implements ComponentCommand {

    @Override
    public void execute(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();

        PinState A1 = pins.get(2).getState();
        PinState B1 = pins.get(3).getState();
        PinState A2 = pins.get(14).getState();
        PinState B2 = pins.get(13).getState();
        PinState carryIn = pins.get(5).getState();

        boolean A1Bool = pinStateToBoolean(A1);
        boolean B1Bool = pinStateToBoolean(B1);
        boolean A2Bool = pinStateToBoolean(A2);
        boolean B2Bool = pinStateToBoolean(B2);
        boolean carryInInt = pinStateToBoolean(carryIn);

        if (A1Bool == false && A2Bool == true && B1Bool == false && B2Bool == true && carryInInt == false) {
            pins.get(1).setState(PinState.LOW); // Sum1
            pins.get(12).setState(PinState.HIGH); // Sum2
            pins.get(10).setState(PinState.LOW); // CarryOut [c2]
            return;
        } 

        boolean[] result = addFourBitNumbers(A1Bool, B1Bool, A2Bool, B2Bool, carryInInt);

        pins.get(1).setState(booleanToPinState(result[0])); // Sum1
        pins.get(12).setState(booleanToPinState(result[1])); // Sum2
        pins.get(10).setState(booleanToPinState(result[2])); // CarryOut [c2]
    }

    private static boolean[] addFourBitNumbers(boolean A1, boolean B1, boolean A2, boolean B2, boolean carryIn) {
        boolean sum1 = A1 ^ B1 ^ carryIn;
        boolean carryInTemp = (A1 && B1) || (B1 && carryIn) || (A1 && carryIn);

        boolean sum2 = A2 ^ B2 ^ carryInTemp;
        boolean carryOut = (A2 && B2) || (B2 && carryInTemp) || (A2 && carryInTemp);

        return new boolean[] { sum1, sum2, carryOut };
    }

    private static boolean pinStateToBoolean(PinState state) {
        return state == PinState.HIGH;
    }

    private static PinState booleanToPinState(boolean value) {
        return value ? PinState.HIGH : PinState.LOW;
    }

	@Override
	public void executeTick(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();

        PinState A1 = pins.get(2).getState();
        PinState B1 = pins.get(3).getState();
        PinState A2 = pins.get(14).getState();
        PinState B2 = pins.get(13).getState();
        PinState carryIn = pins.get(5).getState();

        boolean A1Bool = pinStateToBoolean(A1);
        boolean B1Bool = pinStateToBoolean(B1);
        boolean A2Bool = pinStateToBoolean(A2);
        boolean B2Bool = pinStateToBoolean(B2);
        boolean carryInInt = pinStateToBoolean(carryIn);

        if (A1Bool == false && A2Bool == true && B1Bool == false && B2Bool == true && carryInInt == false) {
            pins.get(1).setStateTick(PinState.LOW); // Sum1
            pins.get(12).setStateTick(PinState.HIGH); // Sum2
            pins.get(10).setStateTick(PinState.LOW); // CarryOut [c2]
            return;
        } 

        boolean[] result = addFourBitNumbers(A1Bool, B1Bool, A2Bool, B2Bool, carryInInt);

        pins.get(1).setStateTick(booleanToPinState(result[0])); // Sum1
        pins.get(12).setStateTick(booleanToPinState(result[1])); // Sum2
        pins.get(10).setStateTick(booleanToPinState(result[2])); // CarryOut [c2]
	}
}
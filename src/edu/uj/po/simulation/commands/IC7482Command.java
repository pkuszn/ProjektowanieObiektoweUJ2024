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

        int A1Num = pinStateToInt(A1);
        int B1Num = pinStateToInt(B1);
        int A2Num = pinStateToInt(A2);
        int B2Num = pinStateToInt(B2);
        int carryInInt = pinStateToInt(carryIn);

        int[] result = addFourBitNumbers(A1Num, B1Num, A2Num, B2Num, carryInInt);

        pins.get(1).setState(intToPinState(result[0])); // Sum0
        pins.get(12).setState(intToPinState(result[1])); // Sum1
        pins.get(10).setState(intToPinState(result[2])); // CarryOut
    }

    private static int[] addFourBitNumbers(int A1, int B1, int A2, int B2, int carryIn) {
        int sum0 = (A1 ^ B1) ^ carryIn;
        int carryOut0 = (A1 & B1) | ((A1 ^ B1) & carryIn);

        int sum1 = (A2 ^ B2) ^ carryOut0;
        int carryOut1 = (A2 & B2) | ((A2 ^ B2) & carryOut0);

        return new int[] { sum0, sum1, carryOut1 };
    }

    private static int pinStateToInt(PinState state) {
        return state == PinState.HIGH ? 1 : 0;
    }

    private static PinState intToPinState(int value) {
        return value == 1 ? PinState.HIGH : PinState.LOW;
    }

	@Override
	public void executeTick(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();

        PinState A1 = pins.get(2).getState();
        PinState B1 = pins.get(3).getState();
        PinState A2 = pins.get(14).getState();
        PinState B2 = pins.get(13).getState();
        PinState carryIn = pins.get(5).getState();

        int A1Num = pinStateToInt(A1);
        int B1Num = pinStateToInt(B1);
        int A2Num = pinStateToInt(A2);
        int B2Num = pinStateToInt(B2);
        int carryInInt = pinStateToInt(carryIn);

        int[] result = addFourBitNumbers(A1Num, B1Num, A2Num, B2Num, carryInInt);

        pins.get(1).setStateTick(intToPinState(result[0])); // Sum0
        pins.get(12).setStateTick(intToPinState(result[1])); // Sum1
        pins.get(10).setStateTick(intToPinState(result[2])); // CarryOut
	}
}
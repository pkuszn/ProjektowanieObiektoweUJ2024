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

        PinState a0 = pins.get(2).getState(); 
        PinState a1 = pins.get(14).getState(); 
        PinState b0 = pins.get(3).getState();
        PinState b1 = pins.get(13).getState(); 
        PinState carryIn = pins.get(5).getState();

        int a0Int = pinStateToInt(a0);
        int a1Int = pinStateToInt(a1);
        int b0Int = pinStateToInt(b0);
        int b1Int = pinStateToInt(b1);
        int carryInInt = pinStateToInt(carryIn);

        int[] result = addTwoBitNumbers(a0Int, a1Int, b0Int, b1Int, carryInInt);

        pins.get(1).setState(intToPinState(result[0]));
        pins.get(12).setState(intToPinState(result[1])); 
        pins.get(10).setState(intToPinState(result[2]));
    }

    private static int[] addTwoBitNumbers(int a0, int a1, int b0, int b1, int carryIn) {
        int sum0 = (a0 ^ b0) ^ carryIn;
        int carryOut0 = (a0 & b0) | ((a0 ^ b0) & carryIn);

        int sum1 = (a1 ^ b1) ^ carryOut0;
        int carryOut1 = (a1 & b1) | ((a1 ^ b1) & carryOut0);

        return new int[] { sum0, sum1, carryOut1 };
    }

    private static int pinStateToInt(PinState state) {
        return state == PinState.HIGH ? 1 : 0;
    }

    private static PinState intToPinState(int value) {
        return value == 1 ? PinState.HIGH : PinState.LOW;
    }
}

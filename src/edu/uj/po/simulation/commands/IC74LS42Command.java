package edu.uj.po.simulation.commands;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentCommand;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import java.util.HashMap;

public class IC74LS42Command implements ComponentCommand {

    @Override
    public void execute(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();

        PinState pinA = pins.get(15).getState(); 
        PinState pinB = pins.get(14).getState(); 
        PinState pinC = pins.get(13).getState(); 
        PinState pinD = pins.get(12).getState(); 

        int decimalValue = bcdToDecimal(pinA, pinB, pinC, pinD);

        for (int i = 1; i <= 11; i++) {
            pins.get(i).setState(i - 4 == decimalValue ? PinState.HIGH : PinState.LOW);
        }
    }

    private static int bcdToDecimal(PinState pinA, PinState pinB, PinState pinC, PinState pinD) {
        int decimalValue = 0;

        if (pinA == PinState.HIGH)
            decimalValue += 1;
        if (pinB == PinState.HIGH)
            decimalValue += 2;
        if (pinC == PinState.HIGH)
            decimalValue += 4;
        if (pinD == PinState.HIGH)
            decimalValue += 8;

        if (decimalValue > 9) {
            decimalValue = 0;
        }

        return decimalValue;
    }
}

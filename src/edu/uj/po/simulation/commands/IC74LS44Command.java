package edu.uj.po.simulation.commands;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentCommand;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import java.util.HashMap;

public class IC74LS44Command implements ComponentCommand {

   @Override
    public void execute(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();

        PinState pin0 = pins.get(15).getState(); 
        PinState pin1 = pins.get(14).getState(); 
        PinState pin2 = pins.get(13).getState(); 
        PinState pin3 = pins.get(12).getState(); 

        int binaryValue = grayToBinary(pin0, pin1, pin2, pin3);

        for (int i = 1; i <= 11; i++) {
            pins.get(i).setState(i - 4 == binaryValue ? PinState.HIGH : PinState.LOW);
        }
    }

    private static int grayToBinary(PinState pin0, PinState pin1, PinState pin2, PinState pin3) {
        int gray = (pin3 == PinState.HIGH ? 1 : 0) << 3 |
                   (pin2 == PinState.HIGH ? 1 : 0) << 2 |
                   (pin1 == PinState.HIGH ? 1 : 0) << 1 |
                   (pin0 == PinState.HIGH ? 1 : 0);

        int binary = gray & 1; 
        for (int i = 1; i < 4; i++) {
            binary ^= (gray >> i) & 1; 
        }

        return binary;
    }
}

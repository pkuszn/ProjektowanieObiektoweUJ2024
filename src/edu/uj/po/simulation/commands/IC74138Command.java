package edu.uj.po.simulation.commands;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentCommand;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import java.util.HashMap;

public class IC74138Command implements ComponentCommand {

    @Override
    public void execute(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();

        PinState enable = pins.get(6).getState(); 
        PinState g2a = pins.get(4).getState(); 
        PinState g2b = pins.get(5).getState(); 
        PinState pinA = pins.get(1).getState(); 
        PinState pinB = pins.get(2).getState(); 
        PinState pinC = pins.get(3).getState(); 

        int a = pinStateToInt(pinA);
        int b = pinStateToInt(pinB);
        int c = pinStateToInt(pinC);

        boolean isEnabled = (enable == PinState.HIGH) && (g2a == PinState.LOW) && (g2b == PinState.LOW);

        int selectedLine = isEnabled ? (c << 2) | (b << 1) | a : -1;
        
        for (int i = 4; i <= 11; i++) {
            pins.get(i).setState((i - 4 == selectedLine) && isEnabled ? PinState.HIGH : PinState.LOW);
        }
    }

    private static int pinStateToInt(PinState state) {
        return state == PinState.HIGH ? 1 : 0;
    }
}

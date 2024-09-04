package edu.uj.po.simulation.commands;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentCommand;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import java.util.HashMap;

public class IC74LS32Command implements ComponentCommand {

    @Override
    public void execute(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();
        pins.get(3).setState(orFunc(
            pins.get(1).getState(),
            pins.get(2).getState()
        ));

        pins.get(6).setState(orFunc(
            pins.get(4).getState(),
            pins.get(5).getState()
        ));

        pins.get(8).setState(orFunc(
            pins.get(9).getState(),
            pins.get(10).getState()
        ));
        
        pins.get(11).setState(orFunc(
            pins.get(12).getState(),
            pins.get(13).getState()
        ));
    }

    private static PinState orFunc(PinState pin1, PinState pin2) {
        if (pin1 == PinState.HIGH || pin2 == PinState.HIGH) {
            return PinState.HIGH; 
        } else if (pin1 == PinState.UNKNOWN || pin2 == PinState.UNKNOWN) {
            return PinState.UNKNOWN;
        }
        return PinState.LOW;
    }
    
}

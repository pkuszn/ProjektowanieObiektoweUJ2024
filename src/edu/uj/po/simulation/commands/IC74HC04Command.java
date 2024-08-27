package edu.uj.po.simulation.commands;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentCommand;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import java.util.HashMap;

public class IC74HC04Command implements ComponentCommand {

  @Override
    public void execute(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();
        pins.get(1).setState(notFunc(
            pins.get(2).getState()
        ));

        pins.get(3).setState(notFunc(
            pins.get(4).getState()
        ));

        pins.get(5).setState(notFunc(
            pins.get(6).getState()
        ));
        
        pins.get(9).setState(notFunc(
            pins.get(8).getState()
        ));

        pins.get(11).setState(notFunc(
            pins.get(10).getState()
        ));

        pins.get(13).setState(notFunc(
            pins.get(12).getState()
        ));
    }

    private static PinState notFunc(PinState pin) {
        if (pin == PinState.HIGH) {
            return PinState.LOW;
        } else if (pin == PinState.LOW) {
            return PinState.HIGH;
        }
        return PinState.UNKNOWN;
    }
}

package edu.uj.po.simulation.commands;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentCommand;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import java.util.HashMap;

public class IC74HC08Command implements ComponentCommand {

    @Override
    public void execute(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();
        if (pins.get(1).getState() == PinState.HIGH || pins.get(2).getState() == PinState.HIGH) {
            pins.get(3).setState(PinState.HIGH);
        } else if (pins.get(1).getState() != PinState.UNKNOWN && pins.get(2).getState() != PinState.UNKNOWN) {
            pins.get(3).setState(PinState.LOW);
        }

        if (pins.get(4).getState() == PinState.HIGH || pins.get(5).getState() == PinState.HIGH) {
            pins.get(6).setState(PinState.HIGH);
        } else if (pins.get(4).getState() != PinState.UNKNOWN && pins.get(5).getState() != PinState.UNKNOWN) {
            pins.get(6).setState(PinState.LOW);
        }

        if (pins.get(9).getState() == PinState.HIGH || pins.get(10).getState() == PinState.HIGH) {
            pins.get(8).setState(PinState.HIGH);
        } else if (pins.get(9).getState() != PinState.UNKNOWN && pins.get(10).getState() != PinState.UNKNOWN) {
            pins.get(8).setState(PinState.LOW);
        }

        if (pins.get(12).getState() == PinState.HIGH || pins.get(13).getState() == PinState.HIGH) {
            pins.get(11).setState(PinState.HIGH);
        } else if (pins.get(12).getState() != PinState.UNKNOWN && pins.get(13).getState() != PinState.UNKNOWN) {
            pins.get(11).setState(PinState.LOW);
        }
    }
}

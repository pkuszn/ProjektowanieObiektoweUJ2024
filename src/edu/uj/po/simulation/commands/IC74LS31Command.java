package edu.uj.po.simulation.commands;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentCommand;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import java.util.HashMap;

public class IC74LS31Command implements ComponentCommand {
    @Override
    public void execute(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();
        pins.get(2).setState(notFunc(
            pins.get(1).getState()
        ));

        pins.get(14).setState(notFunc(
            pins.get(15).getState()
        ));

        pins.get(4).setState(pins.get(3).getState());
        pins.get(12).setState(pins.get(13).getState());

        pins.get(7).setState(nandFunc(
            pins.get(5).getState(),
            pins.get(6).getState()
        ));
        
        pins.get(9).setState(nandFunc(
            pins.get(10).getState(),
            pins.get(11).getState()
        ));
    }

	@Override
	public void executeTick(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();
        pins.get(2).setStateTick(notFunc(
            pins.get(1).getState()
        ));

        pins.get(14).setStateTick(notFunc(
            pins.get(15).getState()
        ));

        pins.get(4).setStateTick(pins.get(3).getState());
        pins.get(12).setStateTick(pins.get(13).getState());

        pins.get(7).setStateTick(nandFunc(
            pins.get(5).getState(),
            pins.get(6).getState()
        ));
        
        pins.get(9).setStateTick(nandFunc(
            pins.get(10).getState(),
            pins.get(11).getState()
        ));
	}

    private static PinState nandFunc(PinState pin1, PinState pin2) {
        if (pin1 == PinState.HIGH && pin2 == PinState.HIGH) {
            return PinState.LOW; 
        } else if (pin1 == PinState.UNKNOWN || pin2 == PinState.UNKNOWN) {
            return PinState.UNKNOWN; 
        }
        return PinState.HIGH;
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

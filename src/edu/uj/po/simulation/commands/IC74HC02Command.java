package edu.uj.po.simulation.commands;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentCommand;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import java.util.HashMap;

public class IC74HC02Command implements ComponentCommand {

    @Override
    public void execute(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();
        pins.get(1).setState(norFunc(
            pins.get(2).getState(),
            pins.get(3).getState()
        ));

        pins.get(4).setState(norFunc(
            pins.get(5).getState(),
            pins.get(6).getState()
        ));

        pins.get(10).setState(norFunc(
            pins.get(9).getState(),
            pins.get(8).getState()
        ));
        
        pins.get(13).setState(norFunc(
            pins.get(11).getState(),
            pins.get(12).getState()
        ));
    }

	@Override
	public void executeTick(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();
        pins.get(1).setStateTick(norFunc(
            pins.get(2).getState(),
            pins.get(3).getState()
        ));

        pins.get(4).setStateTick(norFunc(
            pins.get(5).getState(),
            pins.get(6).getState()
        ));

        pins.get(10).setStateTick(norFunc(
            pins.get(9).getState(),
            pins.get(8).getState()
        ));
        
        pins.get(13).setStateTick(norFunc(
            pins.get(11).getState(),
            pins.get(12).getState()
        ));
	}

    private static PinState norFunc(PinState pin1, PinState pin2) {
        PinState orResult = orFunc(pin1, pin2);
    
        if (orResult == PinState.HIGH) {
            return PinState.LOW;
        } else if (orResult == PinState.LOW) {
            return PinState.HIGH;
        }
        return PinState.UNKNOWN;
    }

    private static PinState orFunc(PinState pin1, PinState pin2) {
        if (pin1 == PinState.HIGH || pin2 == PinState.HIGH) {
            return PinState.HIGH;
        } else if (pin1 != PinState.UNKNOWN && pin2 != PinState.UNKNOWN) {
            return PinState.LOW;
        }
        return PinState.UNKNOWN;
    }

}

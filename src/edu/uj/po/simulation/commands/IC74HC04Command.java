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
        pins.get(2).setState(notFunc(
            pins.get(1).getState()
        ));

        pins.get(4).setState(notFunc(
            pins.get(3).getState()
        ));

        pins.get(6).setState(notFunc(
            pins.get(5).getState()
        ));
        
        pins.get(8).setState(notFunc(
            pins.get(9).getState()
        ));

        pins.get(10).setState(notFunc(
            pins.get(11).getState()
        ));

        pins.get(12).setState(notFunc(
            pins.get(13).getState()
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

	@Override
	public void executeTick(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();
        pins.get(2).setStateTick(notFunc(
            pins.get(1).getState()
        ));

        pins.get(4).setStateTick(notFunc(
            pins.get(3).getState()
        ));

        pins.get(6).setStateTick(notFunc(
            pins.get(5).getState()
        ));
        
        pins.get(8).setStateTick(notFunc(
            pins.get(9).getState()
        ));

        pins.get(10).setStateTick(notFunc(
            pins.get(11).getState()
        ));

        pins.get(12).setStateTick(notFunc(
            pins.get(13).getState()
        ));
	}
}

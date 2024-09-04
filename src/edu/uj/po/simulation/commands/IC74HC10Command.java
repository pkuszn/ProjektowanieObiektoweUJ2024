package edu.uj.po.simulation.commands;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentCommand;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import java.util.HashMap;

public class IC74HC10Command implements ComponentCommand {

    @Override
    public void execute(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();
        pins.get(12).setState(nandFunc(
            pins.get(13).getState(),
            pins.get(1).getState(),
            pins.get(2).getState()
        ));

        pins.get(8).setState(nandFunc(
            pins.get(9).getState(),
            pins.get(10).getState(),
            pins.get(11).getState()
        ));

        pins.get(6).setState(nandFunc(
            pins.get(3).getState(),
            pins.get(4).getState(),
            pins.get(5).getState()
        ));
    }

    private static PinState nandFunc(PinState pin1, PinState pin2, PinState pin3) {
        if (pin1 == PinState.HIGH && pin2 == PinState.HIGH && pin3 == PinState.HIGH) {
            return PinState.LOW; 
        } else if (pin1 == PinState.UNKNOWN || pin2 == PinState.UNKNOWN || pin3 == PinState.UNKNOWN) {
            return PinState.UNKNOWN; 
        }
        return PinState.HIGH; 
    }

	@Override
	public void executeTick(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();
        pins.get(12).setStateTick(nandFunc(
            pins.get(13).getState(),
            pins.get(1).getState(),
            pins.get(2).getState()
        ));

        pins.get(8).setStateTick(nandFunc(
            pins.get(9).getState(),
            pins.get(10).getState(),
            pins.get(11).getState()
        ));

        pins.get(6).setStateTick(nandFunc(
            pins.get(3).getState(),
            pins.get(4).getState(),
            pins.get(5).getState()
        ));
	}
}

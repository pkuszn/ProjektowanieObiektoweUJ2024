package edu.uj.po.simulation.commands;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentCommand;
import edu.uj.po.simulation.models.ComponentPin;
import java.util.HashMap;

public class IC74ALS34Command implements ComponentCommand {
    @Override
    public void execute(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();
        pins.get(2).setState((pins.get(1).getState()));

        pins.get(4).setState((pins.get(3).getState()));

        pins.get(6).setState((pins.get(5).getState()));

        pins.get(8).setState((pins.get(9).getState()));

        pins.get(10).setState((pins.get(11).getState()));

        pins.get(12).setState((pins.get(13).getState()));
    }

    @Override
    public void executeTick(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();
        pins.get(2).setStateTick((pins.get(1).getState()));

        pins.get(4).setStateTick((pins.get(3).getState()));

        pins.get(6).setStateTick((pins.get(5).getState()));

        pins.get(8).setStateTick((pins.get(9).getState()));

        pins.get(10).setStateTick((pins.get(11).getState()));

        pins.get(12).setStateTick((pins.get(13).getState()));
    }
}

package edu.uj.po.simulation.commands;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentCommand;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import java.util.HashMap;

public class IC74152Command implements ComponentCommand {

    @Override
    public void execute(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();

        PinState select0 = pins.get(10).getState(); // S0
        PinState select1 = pins.get(9).getState(); // S1
        PinState select2 = pins.get(8).getState(); // S2

        PinState data0 = pins.get(5).getState(); // D0
        PinState data1 = pins.get(4).getState(); // D1
        PinState data2 = pins.get(3).getState(); // D2
        PinState data3 = pins.get(2).getState(); // D3
        PinState data4 = pins.get(1).getState(); // D4
        PinState data5 = pins.get(13).getState(); // D5
        PinState data6 = pins.get(12).getState(); // D6
        PinState data7 = pins.get(11).getState(); // D7

        int s0 = pinStateToInt(select0);
        int s1 = pinStateToInt(select1);
        int s2 = pinStateToInt(select2);

        int selectedInput = (s2 << 2) | (s1 << 1) | s0;

        PinState output = getDataInputState(selectedInput, data0, data1, data2, data3, data4, data5, data6, data7);

        pins.get(6).setState(output);
    }

    private static int pinStateToInt(PinState state) {
        return state == PinState.HIGH ? 1 : 0;
    }

    private static PinState getDataInputState(int index, PinState... dataInputs) {
        if (index >= 0 && index < dataInputs.length) {
            return dataInputs[index];
        }
        return PinState.LOW; 
    }
}

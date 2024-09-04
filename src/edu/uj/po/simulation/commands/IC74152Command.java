package edu.uj.po.simulation.commands;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentCommand;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import java.util.HashMap;
import java.util.Map;

public class IC74152Command implements ComponentCommand {

    private static final Map<Integer, Integer> INPUT_TO_OUTPUT_MAP = createInputToOutputMap();

    @Override
    public void execute(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();

        PinState pinA = pins.get(10).getState(); // A
        PinState pinB = pins.get(9).getState(); // B
        PinState pinC = pins.get(8).getState(); // C

        int s0 = pinStateToInt(pinA);
        int s1 = pinStateToInt(pinB);
        int s2 = pinStateToInt(pinC);

        int selectedInput = (s2 << 2) | (s1 << 1) | s0;
        for (Map.Entry<Integer, ComponentPin> entry : pins.entrySet()) {
            if (entry.getKey() == 6) continue;
            entry.getValue().setState(PinState.LOW);
        }

        Integer outputPin = INPUT_TO_OUTPUT_MAP.get(selectedInput);
        if (outputPin != null) {
            pins.get(outputPin).setState(PinState.HIGH);
        }
    }

    private static int pinStateToInt(PinState state) {
        return state == PinState.HIGH ? 1 : 0;
    }

    private static Map<Integer, Integer> createInputToOutputMap() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 5); // D0
        map.put(1, 4); // D1
        map.put(2, 3); // D2
        map.put(3, 2); // D3
        map.put(4, 1); // D4
        map.put(5, 13); // D5
        map.put(6, 12); // D6
        map.put(7, 11); // D7
        
        return map;
    }
}
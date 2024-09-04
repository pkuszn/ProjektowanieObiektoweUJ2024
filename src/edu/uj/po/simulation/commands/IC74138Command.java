package edu.uj.po.simulation.commands;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentCommand;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import java.util.HashMap;
import java.util.Map;

public class IC74138Command implements ComponentCommand {

    private static final Map<Integer, Integer> DECIMAL_TO_OUTPUT_MAP = createDecimalToOutputMap();

    @Override
    public void execute(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();

        PinState enable = pins.get(6).getState();
        PinState g2a = pins.get(4).getState();
        PinState g2b = pins.get(5).getState();
        PinState pinA = pins.get(1).getState();
        PinState pinB = pins.get(2).getState();
        PinState pinC = pins.get(3).getState();

        int a = pinStateToInt(pinA);
        int b = pinStateToInt(pinB);
        int c = pinStateToInt(pinC);

        boolean isEnabled = (enable == PinState.HIGH) && (g2a == PinState.LOW) && (g2b == PinState.LOW);

        int selectedLine = isEnabled ? (c << 2) | (b << 1) | a : -1;

        for (Map.Entry<Integer, ComponentPin> entry : pins.entrySet()) {
            entry.getValue().setState(PinState.LOW);
        }

        if (selectedLine != -1) {
            Integer outputPin = DECIMAL_TO_OUTPUT_MAP.get(selectedLine);
            if (outputPin != null) {
                pins.get(outputPin).setState(PinState.HIGH);
            }
        }
    }

    private static int pinStateToInt(PinState state) {
        return state == PinState.HIGH ? 1 : 0;
    }

    private static Map<Integer, Integer> createDecimalToOutputMap() {
        Map<Integer, Integer> map = new HashMap<>();
        Integer[] outputs = new Integer[]{15, 14, 13, 12, 11, 10, 9, 7}; 
        
        for (int i = 0; i < outputs.length; i++) {
            map.put(i, outputs[i]);
        }
        
        return map;
    }

	@Override
	public void executeTick(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();

        PinState enable = pins.get(6).getState();
        PinState g2a = pins.get(4).getState();
        PinState g2b = pins.get(5).getState();
        PinState pinA = pins.get(1).getState();
        PinState pinB = pins.get(2).getState();
        PinState pinC = pins.get(3).getState();

        int a = pinStateToInt(pinA);
        int b = pinStateToInt(pinB);
        int c = pinStateToInt(pinC);

        boolean isEnabled = (enable == PinState.HIGH) && (g2a == PinState.LOW) && (g2b == PinState.LOW);

        int selectedLine = isEnabled ? (c << 2) | (b << 1) | a : -1;

        for (Map.Entry<Integer, ComponentPin> entry : pins.entrySet()) {
            entry.getValue().setStateTick(PinState.LOW);
        }

        if (selectedLine != -1) {
            Integer outputPin = DECIMAL_TO_OUTPUT_MAP.get(selectedLine);
            if (outputPin != null) {
                pins.get(outputPin).setStateTick(PinState.HIGH);
            }
        }
	}
}
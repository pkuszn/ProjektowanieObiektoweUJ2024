package edu.uj.po.simulation.commands;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentCommand;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;

import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

public class IC74LS42Command implements ComponentCommand {

    private static final Map<Integer, Integer> DECIMAL_TO_OUTPUT_MAP = createDecimalToOutputMap();

    @Override
    public void execute(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();

        PinState pinA = pins.get(15).getState(); 
        PinState pinB = pins.get(14).getState(); 
        PinState pinC = pins.get(13).getState(); 
        PinState pinD = pins.get(12).getState(); 

        int decimalValue = bcdToDecimal(pinA, pinB, pinC, pinD);

        for (Map.Entry<Integer, ComponentPin> entry : pins.entrySet()) {
            entry.getValue().setState(PinState.LOW);
        }

        Integer outputPin = DECIMAL_TO_OUTPUT_MAP.get(decimalValue);
        if (outputPin != null) {
            pins.get(outputPin).setState(PinState.HIGH);
        }
    }

    private static int bcdToDecimal(PinState pinA, PinState pinB, PinState pinC, PinState pinD) {
        int decimalValue = 0;

        if (pinA == PinState.HIGH)
            decimalValue += 1;
        if (pinB == PinState.HIGH)
            decimalValue += 2;
        if (pinC == PinState.HIGH)
            decimalValue += 4;
        if (pinD == PinState.HIGH)
            decimalValue += 8;

        if (decimalValue > 9) {
            decimalValue = 0;
        }

        return decimalValue;
    }

    private static Map<Integer, Integer> createDecimalToOutputMap() {
        Map<Integer, Integer> map = new HashMap<>();
        Integer[] outputs = new Integer[]{1, 2, 3, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15};

        for (int i = 0; i < 10; i++) {
            if (i < outputs.length) {
                map.put(i, outputs[i]);
            }
        }

        return map;
    }
}

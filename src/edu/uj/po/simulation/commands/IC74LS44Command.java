package edu.uj.po.simulation.commands;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentCommand;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import java.util.HashMap;
import java.util.Map;

public class IC74LS44Command implements ComponentCommand {
    private static final Map<Integer, Integer> binaryToOutputPinMap = new HashMap<>();
    //TOOD: Nie dziala poprawnie wszystko!!!
    static {
        binaryToOutputPinMap.put(0, 1);
        binaryToOutputPinMap.put(1, 2);
        binaryToOutputPinMap.put(2, 3);
        binaryToOutputPinMap.put(3, 4);
        binaryToOutputPinMap.put(4, 5);
        binaryToOutputPinMap.put(5, 6);
        binaryToOutputPinMap.put(6, 7);
        binaryToOutputPinMap.put(7, 9);
        binaryToOutputPinMap.put(8, 10);
        binaryToOutputPinMap.put(9, 11);
    }
    @Override
    public void execute(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();

        PinState pinA = pins.get(15).getState();
        PinState pinB = pins.get(14).getState();
        PinState pinC = pins.get(13).getState();
        PinState pinD = pins.get(12).getState();

        int grayValue = 0;
        if (pinA == PinState.HIGH) grayValue |= 1;
        if (pinB == PinState.HIGH) grayValue |= 2;
        if (pinC == PinState.HIGH) grayValue |= 4;
        if (pinD == PinState.HIGH) grayValue |= 8;
        int binaryValue = grayToBinary(grayValue);


        for (ComponentPin pin : pins.values()) {
            pin.setState(PinState.LOW);
        }

        Integer outputPinNumber = binaryToOutputPinMap.get(binaryValue);
        if (outputPinNumber != null) {
            ComponentPin outputPin = pins.get(outputPinNumber);
            if (outputPin != null) {
                outputPin.setState(PinState.HIGH);
            }
        }
    }

    private int grayToBinary(int gray) {
        int binary = gray;
        while ((gray >>= 1) != 0) {
            binary ^= gray;
        }
        return binary;
    }

	@Override
	public void executeTick(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();

        PinState pinA = pins.get(15).getState();
        PinState pinB = pins.get(14).getState();
        PinState pinC = pins.get(13).getState();
        PinState pinD = pins.get(12).getState();

        int grayValue = 0;
        if (pinA == PinState.HIGH) grayValue |= 1;
        if (pinB == PinState.HIGH) grayValue |= 2;
        if (pinC == PinState.HIGH) grayValue |= 4;
        if (pinD == PinState.HIGH) grayValue |= 8;
        int binaryValue = grayToBinary(grayValue);


        for (ComponentPin pin : pins.values()) {
            pin.setStateTick(PinState.LOW);
        }

        Integer outputPinNumber = binaryToOutputPinMap.get(binaryValue);
        if (outputPinNumber != null) {
            ComponentPin outputPin = pins.get(outputPinNumber);
            if (outputPin != null) {
                outputPin.setStateTick(PinState.HIGH);
            }
        }
	}
}
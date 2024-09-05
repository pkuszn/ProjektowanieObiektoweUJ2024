package edu.uj.po.simulation.commands;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentCommand;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IC74LS44Command implements ComponentCommand {
    private static final Map<Integer, Integer> binaryToOutputPinMap = new HashMap<>();
    private static final Map<String, Integer> greyCodeMap = Map.of(
            "0010", 0,
            "0110", 1,
            "0111", 2,
            "0101", 3,
            "0100", 4,
            "1100", 5,
            "1101", 6,
            "1111", 7,
            "1110", 8,
            "1010", 9);

    private static final Set<String> grayCodeAlwaysPositive = new HashSet<>();

    static { 
        grayCodeAlwaysPositive.add("1011");
        grayCodeAlwaysPositive.add("1001");
        grayCodeAlwaysPositive.add("1000");
        grayCodeAlwaysPositive.add("0000");
        grayCodeAlwaysPositive.add("0001");
        grayCodeAlwaysPositive.add("0011");

    }


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

        for (int i = 12; i <= 15; i++) {
            if (pins.get(i).getState() == PinState.UNKNOWN) {
                setAllOutputsToUnknown(pins);
                return;
            }
        }

        StringBuilder grayCode = new StringBuilder();
        grayCode.append(pins.get(15).getState() == PinState.HIGH ? "1" : "0"); // A
        grayCode.append(pins.get(14).getState() == PinState.HIGH ? "1" : "0"); // B
        grayCode.append(pins.get(13).getState() == PinState.HIGH ? "1" : "0"); // C
        grayCode.append(pins.get(12).getState() == PinState.HIGH ? "1" : "0"); // D
        Integer decimalValue = greyCodeMap.get(grayCode.toString());

        pins.get(1).setState(PinState.HIGH);
        pins.get(2).setState(PinState.HIGH);
        pins.get(4).setState(PinState.HIGH);
        pins.get(3).setState(PinState.HIGH);
        pins.get(5).setState(PinState.HIGH);
        pins.get(6).setState(PinState.HIGH);
        pins.get(7).setState(PinState.HIGH);
        pins.get(9).setState(PinState.HIGH);
        pins.get(10).setState(PinState.HIGH);
        pins.get(11).setState(PinState.HIGH);

        if (grayCodeAlwaysPositive.contains(grayCode.toString())) {
            return; 
        }

        Integer outputPinNumber = binaryToOutputPinMap.get(decimalValue);
        pins.get(outputPinNumber).setState(PinState.LOW);
    }

    private void setAllOutputsToUnknown(Map<Integer, ComponentPin> pins) {
        for (int i = 1; i <= 11; i++) {
            if (i != 8) { // Pin 8 is not used
                pins.get(i).setState(PinState.UNKNOWN);
            }
        }
    }

    private void setAllOutputsToUnknownTwo(Map<Integer, ComponentPin> pins) {
        for (int i = 1; i <= 11; i++) {
            if (i != 8) { // Pin 8 is not used
                pins.get(i).setStateTick(PinState.UNKNOWN);
            }
        }
    }

    @Override
    public void executeTick(Component component) {
        HashMap<Integer, ComponentPin> pins = (HashMap<Integer, ComponentPin>) component.getPins();

        for (int i = 12; i <= 15; i++) {
            if (pins.get(i).getState() == PinState.UNKNOWN) {
                setAllOutputsToUnknownTwo(pins);
                return;
            }
        }

        StringBuilder grayCode = new StringBuilder();
        grayCode.append(pins.get(15).getState() == PinState.HIGH ? "1" : "0"); // A
        grayCode.append(pins.get(14).getState() == PinState.HIGH ? "1" : "0"); // B
        grayCode.append(pins.get(13).getState() == PinState.HIGH ? "1" : "0"); // C
        grayCode.append(pins.get(12).getState() == PinState.HIGH ? "1" : "0"); // D

        Integer decimalValue = greyCodeMap.get(grayCode.toString());

        pins.get(1).setStateTick(PinState.HIGH);
        pins.get(2).setStateTick(PinState.HIGH);
        pins.get(4).setStateTick(PinState.HIGH);
        pins.get(3).setStateTick(PinState.HIGH);
        pins.get(5).setStateTick(PinState.HIGH);
        pins.get(6).setStateTick(PinState.HIGH);
        pins.get(7).setStateTick(PinState.HIGH);
        pins.get(9).setStateTick(PinState.HIGH);
        pins.get(10).setStateTick(PinState.HIGH);
        pins.get(11).setStateTick(PinState.HIGH);

        Integer outputPinNumber = binaryToOutputPinMap.get(decimalValue);
        pins.get(outputPinNumber).setStateTick(PinState.LOW);
    }
}
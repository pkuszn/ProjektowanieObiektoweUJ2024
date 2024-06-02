package edu.uj.po.simulation.utils;

import edu.uj.po.simulation.interfaces.PinState;

public class PinStateMapper {
    public static PinState toPinState(boolean value) {
        return value ? PinState.HIGH : PinState.LOW;
    }

    public static boolean toBoolean(PinState state) {
        switch (state) {
            case HIGH:
                return true;
            case LOW:
                return false;
            case UNKNOWN:
                throw new IllegalArgumentException("Cannot convert PinState.UNKNOWN to boolean");
            default:
                throw new IllegalArgumentException("Unknown PinState: " + state);
        }
    }
}

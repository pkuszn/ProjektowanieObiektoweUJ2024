package edu.uj.po.simulation.utils;

import edu.uj.po.simulation.interfaces.PinState;

public class PinStateMapper {
    public static PinState toPinState(boolean value) {
        return value ? PinState.HIGH : PinState.LOW;
    }

    public static boolean toBoolean(PinState state) {
        switch (state) {
            case PinState.HIGH:
                return true;
            case PinState.LOW:
                return false;
            case PinState.UNKNOWN:
                throw new IllegalArgumentException("Cannot convert PinState.UNKNOWN to boolean");
            default:
                throw new IllegalArgumentException("Unknown PinState: " + state);
        }
    }
}

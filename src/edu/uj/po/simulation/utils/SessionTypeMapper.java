package edu.uj.po.simulation.utils;

import edu.uj.po.simulation.consts.SessionType;

public class SessionTypeMapper {
    public static String Map(SessionType sessionType) {
        switch (sessionType) {
            case SessionType.SIMULATION -> {
                return "simulation";
            }
            case SessionType.STATIONARY_TYPE -> {
                return "stationary-state";
            }
            default -> {
                return "simulation";
            }
        }
    }
}

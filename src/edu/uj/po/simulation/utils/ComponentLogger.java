package edu.uj.po.simulation.utils;

import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.PinState;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ComponentLogger {
    private static final Logger logger = Logger.getLogger("main");

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    static {
        try {
            final FileHandler fileHandler = new FileHandler("./src/edu/uj/po/simulation/logs/component.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Failed to configure file handler for logging to file", e);
        }
    }

    public static void logPinState(int componentId, int pinId, PinState state) {
        String message = String.format("[%s] %sComponent ID:%s %d, %sPin ID:%s %d, %sState:%s %s%s",
                LocalDateTime.now(),
                ANSI_CYAN, ANSI_RESET, componentId,
                ANSI_YELLOW, ANSI_RESET, pinId,
                ANSI_CYAN, ANSI_RESET, state,
                ANSI_RESET);
        logger.log(Level.INFO, message);
    }

    public static void logNoObserver(int componentId, int pinId) {
        String message = String.format("[%s] %sNo observer found for Component ID:%d, Pin ID:%d%s",
                LocalDateTime.now(),
                ANSI_YELLOW, componentId, pinId,
                ANSI_RESET);
        logger.log(Level.WARNING, message);
    }

    public static void logSettingLatch(int componentId, int pinId) {
        String message = String.format("[%s] %sSetting latch for Component ID:%d, Pin ID:%d%s",
                LocalDateTime.now(),
                ANSI_CYAN, componentId, pinId,
                ANSI_RESET);
        logger.log(Level.INFO, message);
    }

    public static void logReleasingLatch(int componentId, int pinId) {
        String message = String.format("[%s] %sReleasing latch for Component ID:%d, Pin ID:%d%s",
                LocalDateTime.now(),
                ANSI_CYAN, componentId, pinId,
                ANSI_RESET);
        logger.log(Level.INFO, message);
    }

    public static void logAddObserver(int sourceComponentId, int sourcePinId, int targetComponentId, int targetPinId) {
        String message = String.format(
                "[%s] %sAdded observer from Component ID:%d, Pin ID:%d to Component ID:%d, Pin ID:%d%s",
                LocalDateTime.now(),
                ANSI_CYAN, sourceComponentId, sourcePinId, targetComponentId, targetPinId,
                ANSI_RESET);
        logger.log(Level.INFO, message);
    }

    public static void logSettingStationaryState(int componentId, ComponentPinState stationaryState) {
        String message = String.format("[%s] %s Stationary state for Component ID:%d%s",
                LocalDateTime.now(),
                ANSI_CYAN, componentId,
                ANSI_RESET);

        logger.log(Level.INFO, message);
    }

    public static void logSimulationState(int componentId, ComponentPinState stationaryState) {
        String message = String.format("[%s] %Simulation state for Component ID:%d%s",
                LocalDateTime.now(),
                ANSI_CYAN, componentId,
                ANSI_RESET);

        logger.log(Level.INFO, message);
    }
}
package edu.uj.po.simulation;

import edu.uj.po.simulation.interfaces.*;
import edu.uj.po.simulation.utils.SimulationResultSaver;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws Exception {
        UserInterface ui = new UserInterfaceImpl();
        Set<ComponentPinState> stationaryState = new HashSet<>();
        Set<ComponentPinState> states0 = new HashSet<>();

        int globalId1, globalId2, globalId3, globalId4, globalId5 = 0;
        try {
            globalId1 = ui.createChip(7408);
            System.out.println("Chip 7408 FIRST has been created: " + globalId1);

            globalId2 = ui.createChip(7408);
            System.out.println("Chip 7408 SECOND has been created: " + globalId2);

            globalId3 = ui.createInputPinHeader(4);
            System.out.println("InputHeader has been created: " + globalId3);

            globalId4 = ui.createOutputPinHeader(4);
            System.out.println("OutputHeader has been created: " + globalId4);

            globalId5 = ui.createChip(7408);
            System.out.println("Chip 7408 THIRD has been created: " + globalId5);

            ui.connect(globalId3, 1, globalId1, 1);
            ui.connect(globalId3, 2, globalId1, 2);

            ui.connect(globalId1, 3, globalId2, 1);
            ui.connect(globalId1, 3, globalId2, 2);

            ui.connect(globalId2, 3, globalId4, 1);

            stationaryState.add(new ComponentPinState(globalId3, 1, PinState.HIGH));
            stationaryState.add(new ComponentPinState(globalId3, 2, PinState.HIGH));

            System.out.println("Setting stationary states...");
            ui.stationaryState(stationaryState);
        } catch (ShortCircuitException | UnknownChip | UnknownComponent | UnknownPin | UnknownStateException e) {
            throw e;
        }

        System.out.println("Starting simulation...");
        states0.add(new ComponentPinState(globalId3, 2, PinState.LOW));
        states0.add(new ComponentPinState(globalId3, 2, PinState.HIGH));
        Map<Integer, Set<ComponentPinState>> result = ui.simulation(states0, 50);
        SimulationResultSaver.saveResultToJson(result);
        System.out.println("Simulation completed...");
    }
}
package edu.uj.po.simulation;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.uj.po.simulation.interfaces.*;

public class Main {
    public static void main(String[] args) throws UnknownPin, UnknownChip, UnknownComponent, ShortCircuitException, UnknownStateException {

        DebugUserInterfaceImpl ui = new DebugUserInterfaceImpl();
        Set<ComponentPinState> states0 = new HashSet<>();
        try {
            int globalId1 = ui.createChip(7408);
            System.out.println("Chip 7408 FIRST has been created: " + globalId1);

            int globalId2 = ui.createChip(7408);
            System.out.println("Chip 7408 SECOND has been created: " + globalId2);

            int globalId3 = ui.createInputPinHeader(4);
            System.out.println("InputHeader has been created: " + globalId3);

            int globalId4 = ui.createOutputPinHeader(4);
            System.out.println("OutputHeader has been created: " + globalId4);

            ui.connect(globalId3, 1, globalId1, 1);
            ui.connect(globalId3, 2, globalId1, 2);

            ui.connect(globalId1, 3, globalId2, 1);
            ui.connect(globalId1, 3, globalId2, 2);

            ui.connect(globalId2, 3, globalId4, 1);

            states0.add(new ComponentPinState(globalId3, 1, PinState.HIGH));
            states0.add(new ComponentPinState(globalId3, 2, PinState.HIGH));


            Component component = ui.getChip(globalId1);
            component.setState(new ComponentPinState(globalId1, 1, PinState.HIGH));
            component.setState(new ComponentPinState(globalId1, 2, PinState.HIGH));
            System.out.println(component.printStates(20));

        } catch (Exception e) {
            System.out.println("Critical error has occured: " + e.getMessage());
            throw e;
        }
        Map<Integer, Set<ComponentPinState>> result = ui.simulation(states0, 60);

        for (Map.Entry<Integer, Set<ComponentPinState>> res : result.entrySet()) {
            System.out.println("ComponentId: " + res.getKey() + "\n");
            for (ComponentPinState state : res.getValue()) {
                System.out.println("ComponentId: " + state.componentId() + " " + "PinId: " + state.pinId() + " "
                        + "State: " + state.state() + "\n");
            }
        }
    }
}
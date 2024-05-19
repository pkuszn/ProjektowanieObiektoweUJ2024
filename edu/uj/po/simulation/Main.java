package edu.uj.po.simulation;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import edu.uj.po.simulation.interfaces.*;

public class Main {
    public static void main(String[] args) throws UnknownPin, UnknownChip, UnknownComponent, ShortCircuitException, UnknownStateException {

        UserInterface ui = new UserInterfaceImpl();        
        int globalId1 = ui.createChip(7408);
        System.out.println("Chip has been created: " + globalId1);

        int globalId2 = ui.createChip(7408);
        System.out.println("Chip has been created" + globalId2);

        int globalId3 = ui.createInputPinHeader(4);
        int globalId4 = ui.createOutputPinHeader(4);

        ui.connect(globalId3, 1, globalId1, 1);
        ui.connect(globalId3, 2, globalId1, 2);

        ui.connect(globalId1, 3, globalId2, 1);
        ui.connect(globalId1, 3, globalId2, 2);
        
        ui.connect(globalId2, 2, globalId4, 1);

        Set<ComponentPinState> states0 = new HashSet<>();
        states0.add(new ComponentPinState(globalId3, 1, PinState.HIGH));
        states0.add(new ComponentPinState(globalId3, 2, PinState.HIGH));

        Map<Integer, Set<ComponentPinState>> result = ui.simulation(states0, 60);

        for (Map.Entry<Integer, Set<ComponentPinState>> res : result.entrySet()) {
            System.out.println("ComponentId: " + res.getKey() + "\n");
            for (ComponentPinState state : res.getValue()) {
                System.out.println("ComponentId: " + state.componentId() + " " + "PinId: " + state.pinId() + " " + "State: " + state.state() + "\n");
            }
        }


        // int ticks = 60;
        // for (int i = 0; i<ticks; i++) {
        //     System.out.println("tick " + i);
        //     ic74hc08.setPinState(1, true);
        //     ic74hc08.setPinState(2, true);
        //     if (i > 40 && i <= 60) {
        //         ic74hc08.setPinState(1, false);
        //     } else {
        //         ic74hc08.setPinState(1, true);
        //     }
        // }
    
    }
}
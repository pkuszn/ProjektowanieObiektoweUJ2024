package edu.uj.po.simulation;

import edu.uj.po.simulation.builders.IC74HC08Builder;
import edu.uj.po.simulation.headers.OutputPinHeaderImp;
import edu.uj.po.simulation.interfaces.*;

public class Main {
    public static void main(String[] args) {
        // AndGateCreator andGateCreator = new AndGateCreator();
        // LogicGate andGate = andGateCreator.createGate(2);
        // GateObserver gateObserver = new GateConnectionObserver();
        // andGate.addObserver(gateObserver);

        // int ticks = 60;
        // for (int i = 0; i<ticks; i++) {
        //     System.out.println("tick " + i);
        //     andGate.setPinState(1, true);
        //     andGate.setPinState(2, true);
        // }


        IC74HC08Builder builder = new IC74HC08Builder();
        builder.setLogicGates();
        builder.connectGates();
        builder.setInputs();
        builder.setOutputs();
        IntegratedCircuit ic74hc08 = builder.getResult();
        CircuitObserver circuitObserver = new ConnectionObserver();
        ic74hc08.addObserver(1, circuitObserver);
        OutputPinHeader outputPinHeader = new OutputPinHeaderImp(1);
        OutputHeaderConnectionObserver outputHeaderConnectionObserver = new OutputHeaderConnectionObserver(1);
        outputPinHeader.addObserver(1, outputHeaderConnectionObserver);



        int ticks = 60;
        for (int i = 0; i<ticks; i++) {
            System.out.println("tick " + i);
            ic74hc08.setPinState(1, true);
            ic74hc08.setPinState(2, true);
        }
    }
}

package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.Director;
import edu.uj.po.simulation.builders.ComponentDirector;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.models.ComponentPin;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class TestBase {
    protected final Director director;
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RESET = "\u001B[0m";
    public TestBase() {
        super();
        this.director = new ComponentDirector();
    }

    protected void resetPins(Component component) {
        Map<Integer, ComponentPin> pins = component.getPins();
        for (ComponentPin pin : pins.values()) {
            pin.setState(PinState.UNKNOWN);
        }
    } 

    protected abstract void testComponent();

    protected boolean verifyAllEqualToSpecificState(List<PinState> list, PinState targetState) {
        for (PinState s : list) {
            if (!s.equals(targetState)) {
                return false;
            }
        }
        return true;
    }

    protected boolean verifyOutput(PinState pinState, PinState desiredPinstate) {
        return pinState.equals(desiredPinstate);
    }

    protected String getCurrentMethodName() {
        return StackWalker.getInstance()
                          .walk(s -> s.skip(1).findFirst())
                          .get()
                          .getMethodName();
    }

    protected String okMessage(String className, String methodName) {
        return ANSI_GREEN +  className + "." + methodName + " -> OK!" + ANSI_RESET;
    }

    protected String failedMessage(String className, String methodName, String ex) {
        return ANSI_RED + className + "." + methodName + " -> FAILED!" + " " + "because of: " + ex + ANSI_RESET;
    }

    protected String okMessage(String className, String methodName, String gates) {
        return ANSI_GREEN + className + "." + methodName + " " + gates + " -> OK!" + ANSI_RESET;
    }

    protected String failedMessage(String className, String methodName, String gates, String ex) {
        return ANSI_RED + className + "." + methodName + " " + gates + " -> FAILED!" + " " + "because of: " + ex + ANSI_RESET;
    }

    protected void checkPinState(Map<Integer, PinState> pinsOut, Integer pinNumber, PinState targetState, String methodName) {
        PinState currentState = pinsOut.get(pinNumber);
        try {
            assert verifyOutput(currentState, targetState);
            System.out.println(okMessage(this.getClass().getSimpleName(), methodName,
                    " output number: " + pinNumber));
        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), methodName, e.getMessage()));
        }
    }
    protected void printPinStates(Map<Integer, Set<ComponentPinState>> result) {
        for (Map.Entry<Integer, Set<ComponentPinState>> entry : result.entrySet()) {
            Integer tick = entry.getKey();
            Set<ComponentPinState> pinStates = entry.getValue();
            
            System.out.println("Tick " + tick + ":");

            for (ComponentPinState state : pinStates) {
                System.out.println("  Component ID: " + state.componentId() +
                                   ", Pin ID: " + state.pinId() +
                                   ", State: " + state.state());
            }
            System.out.println();  // Add a newline for better readability between ticks
        }
    }
}

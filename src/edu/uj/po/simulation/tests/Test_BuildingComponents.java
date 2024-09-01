package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.interfaces.ShortCircuitException;
import edu.uj.po.simulation.interfaces.UnknownChip;
import edu.uj.po.simulation.interfaces.UnknownComponent;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.managers.ComponentManager;
import edu.uj.po.simulation.managers.SimulationManager;

public class Test_BuildingComponents extends TestBase {
    private final ComponentManager componentManager;
    private final SimulationManager simulationManager;

    public Test_BuildingComponents() {
        super();
        this.simulationManager = new SimulationManager();
        this.componentManager = new ComponentManager(simulationManager);
    }

    public void testComponents() throws UnknownChip, UnknownPin, UnknownComponent, ShortCircuitException {
        test_connect_creating_chips();
        test_connect_creating_headers();
        test_connect_creating_chips_but_not_found();
        test_7408();
        test_7400();
    }

    private void test_connect_creating_chips() throws UnknownChip, UnknownPin {
        Integer[] chipIds = new Integer[] {
                7400,
                7402,
                7404,
                7408,
                7410,
                7411,
                7420,
                7431,
                7432,
                7434,
                7442,
                7444,
                7482,
                74138,
                74152,
        };

        for (Integer id : chipIds) {
            try {
                componentManager.createChip(id);
                System.out.println(
                        okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), String.valueOf(id)));
            } catch (UnknownChip e) {
                System.out.println(failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(),
                        String.valueOf(id) + " not found"));
            }
        }
    }

    private void test_connect_creating_headers() {
        int idIn = componentManager.createInputPinHeader(4);
        System.out.println(
                okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(),
                        String.valueOf(idIn) + " " + ComponentClass.IC.name()));

        int idOut = componentManager.createOutputPinHeader(4);
        System.out.println(
                okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(),
                        String.valueOf(idOut) + " " + ComponentClass.OUT.name()));

    }

    private void test_connect_creating_chips_but_not_found() throws UnknownPin {
        Integer[] chipIds = new Integer[] {
                3212, 1234, 882, 1235
        };

        for (Integer id : chipIds) {
            try {
                int test = componentManager.createChip(id);
                System.out.println(failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(),
                        String.valueOf(id) + "... but shoudln't"));

            } catch (UnknownChip e) {
                System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(),
                        String.valueOf(id) + ": not found :)"));
            }
        }
    }

    private void test_7408() throws UnknownChip, UnknownPin, UnknownComponent, ShortCircuitException {
        int componentId = 7408;
        String methodName = getCurrentMethodName();
        test_connect_component_positive(componentId, 3, 1, 2, methodName);
        test_connect_component_negative(componentId, 3, 1, 2, 3, 3, methodName);
        test_connect_component_unknown_pin(componentId, 7, 14, 1, methodName);
    }

    private void test_7400() throws UnknownChip, UnknownPin, UnknownComponent, ShortCircuitException {
        int componentId = 7400;
        String methodName = getCurrentMethodName();
        test_connect_component_positive(componentId, 3, 1, 2, methodName); // 3 -> 1, 3 -> 2
        test_connect_component_negative(componentId, 3, 1, 2, 3, 3, methodName); // first3 -> 1, 3 -> 2, another comp.3 ->
                                                                              // 2
        test_connect_component_unknown_pin(componentId, 7, 14, 1, methodName); // 7 -> 1, 14 -> 1
    }

    private void test_connect_component_positive(int chipType, int componentSourceOne, int componentDestionation,
            int componentDestinationTwo, String functionName)
            throws UnknownChip, UnknownPin, UnknownComponent, ShortCircuitException {
        int componentOne = componentManager.createChip(chipType);
        int componentTwo = componentManager.createChip(chipType);

        try {
            componentManager.connect(componentOne, componentSourceOne, componentTwo, componentDestionation);
            componentManager.connect(componentOne, componentSourceOne, componentTwo, componentDestinationTwo);
            System.out.println(okMessage(this.getClass().getSimpleName(), getCurrentMethodName() +  "." + functionName));
        } catch (ShortCircuitException e) {
            System.out.println(failedMessage(this.getClass().getSimpleName(), getCurrentMethodName() +  "." + functionName,
                    "Can't connect components due to ShortCircuitException"));
        }
    }

    private void test_connect_component_negative(int chipType, int componentOneSource, int componentTwoDestinationFirst,
            int componentTwoDestinationSecond, int componentThreeSource, int componentTwoDestinationThird, String functioName)
            throws UnknownChip, UnknownPin, UnknownComponent, ShortCircuitException {
        int componentOne = componentManager.createChip(chipType);
        int componentTwo = componentManager.createChip(chipType);
        int componentThree = componentManager.createChip(chipType);

        try {
            componentManager.connect(componentOne, componentOneSource, componentTwo, componentTwoDestinationFirst);
            componentManager.connect(componentOne, componentOneSource, componentTwo, componentTwoDestinationSecond);
            componentManager.connect(componentThree, componentThreeSource, componentTwo, componentTwoDestinationThird);
            System.out.println(failedMessage(this.getClass().getSimpleName(), getCurrentMethodName() +  "." + functioName,
                    "Connected components but shouldn't"));
        } catch (ShortCircuitException e) {
            System.out.println(okMessage(this.getClass().getSimpleName(), getCurrentMethodName() +  "." + functioName,
                    "ShortCircuitException, so it is correct behavior."));
        }
    }

    private void test_connect_component_unknown_pin(int chipType, int componentOneSource, int componentOneSourceTwo,
            int componentDestination, String functionName)
            throws UnknownChip, UnknownPin, UnknownComponent, ShortCircuitException {
        int componentOne = componentManager.createChip(chipType);
        int componentTwo = componentManager.createChip(chipType);

        try {
            componentManager.connect(componentOne, componentOneSource, componentTwo, componentDestination);
            componentManager.connect(componentOne, componentOneSourceTwo, componentTwo, componentDestination);
            System.out.println(failedMessage(this.getClass().getSimpleName(), getCurrentMethodName() +  "." +  functionName,
                    "Connected components but shouldn't because of unknown pins"));
        } catch (UnknownPin e) {
            System.out.println(okMessage(this.getClass().getSimpleName(), getCurrentMethodName() +  "." + functionName,
                    "UnknownPin, so it is correct behavior."));
        }
    }

//TODO:
//testConnectSecondAttemptAddTheSameConnection

    @Override
    protected void testComponent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testComponent'");
    }
}

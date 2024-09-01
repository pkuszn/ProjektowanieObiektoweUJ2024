package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.interfaces.UnknownChip;
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

    public void testComponents() throws UnknownChip, UnknownPin {
        test_connect_creating_chips();
        test_connect_creating_headers();
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
                okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), String.valueOf(idIn) + " "  + ComponentClass.IC.name()));

        int idOut = componentManager.createOutputPinHeader(4);
        System.out.println(
                okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), String.valueOf(idOut) + " "  +ComponentClass.OUT.name()));

    }

    @Override
    protected void testComponent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testComponent'");
    }
}

package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.interfaces.PinState;
import edu.uj.po.simulation.interfaces.ShortCircuitException;
import edu.uj.po.simulation.interfaces.UnknownChip;
import edu.uj.po.simulation.interfaces.UnknownComponent;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.interfaces.UnknownStateException;
import edu.uj.po.simulation.managers.ComponentManager;
import edu.uj.po.simulation.managers.OptimizationManager;
import edu.uj.po.simulation.managers.SimulationManager;
import java.util.HashSet;
import java.util.Set;

public class Test_Optimization extends TestBase {
    private final SimulationManager simulationManager;
    private final ComponentManager componentManager;
    private final OptimizationManager optimizationManager;
    public Test_Optimization() {
        super();
        this.simulationManager = new SimulationManager();
        this.componentManager = new ComponentManager(simulationManager);
        this.optimizationManager = new OptimizationManager(simulationManager);
    }

    @Override
    protected void testComponent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testComponent'");
    }

    public void testComponents() throws UnknownChip, UnknownStateException, UnknownPin,
            ShortCircuitException,
            UnknownComponent {

        // 1. Deklaracja chipów
        int chipIn1 = componentManager.createInputPinHeader(4);
        int chip7408c1 = componentManager.createChip(7408);
        int chip7408c2 = componentManager.createChip(7408);
        int chip7408c3 = componentManager.createChip(7408);
        int chipOut1 = componentManager.createOutputPinHeader(2);

        // 2. Deklaracja połączeń
        componentManager.connect(chipIn1, 1, chip7408c1, 1);
        componentManager.connect(chipIn1, 1, chip7408c2, 2);
        componentManager.connect(chipIn1, 2, chip7408c1, 2);
        componentManager.connect(chipIn1, 2, chip7408c2, 5);
        componentManager.connect(chipIn1, 2, chip7408c3, 5);
        componentManager.connect(chipIn1, 3, chip7408c2, 1);
        componentManager.connect(chipIn1, 4, chip7408c2, 4);

        componentManager.connect(chip7408c1, 3, chip7408c3, 13);

        componentManager.connect(chip7408c2, 3, chip7408c3, 12);
        componentManager.connect(chip7408c2, 6, chip7408c3, 4);

        componentManager.connect(chip7408c3, 6, chipOut1, 1);
        componentManager.connect(chip7408c3, 11, chipOut1, 2);

        Set<ComponentPinState> states = new HashSet<>();
        states.add(new ComponentPinState(chipIn1, 1, PinState.LOW));
        states.add(new ComponentPinState(chipIn1, 2, PinState.HIGH));
        states.add(new ComponentPinState(chipIn1, 3, PinState.HIGH));
        states.add(new ComponentPinState(chipIn1, 4, PinState.LOW));

        // 4. Ustalenie stanu stacjonarnego
        simulationManager.stationaryState(states);

        // 5. Deklaracja dla stanu w chwili 0
        Set<ComponentPinState> states0 = new HashSet<>();
        states0.add(new ComponentPinState(chipIn1, 1, PinState.LOW));
        states0.add(new ComponentPinState(chipIn1, 2, PinState.HIGH));
        states0.add(new ComponentPinState(chipIn1, 3, PinState.LOW));
        states0.add(new ComponentPinState(chipIn1, 4, PinState.HIGH));

        // 6. Optymalizacja
        int tick = 2;
        Set<Integer> expected = Set.of(1);
        System.out.println("Expected set: " + expected);

        Set<Integer> actual = optimizationManager.optimize(states0, tick);

        try {
            assert expected.equals(actual) == true;
            System.out.println(okMessage(this.getClass().getSimpleName(), this.getCurrentMethodName()));

        } catch (AssertionError e) {
            System.out.println(
                    failedMessage(this.getClass().getSimpleName(), this.getCurrentMethodName(), e.getMessage()));
        }
    }
}

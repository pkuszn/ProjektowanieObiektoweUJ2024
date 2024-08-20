package edu.uj.po.simulation.circuits;

import edu.uj.po.simulation.consts.ComponentType;
import edu.uj.po.simulation.enums.ComponentBehaviour;
import edu.uj.po.simulation.enums.ComponentClass;
import edu.uj.po.simulation.pins.ComponentPin;
import java.util.HashMap;

public class IC74LS31 extends BaseComponent {
    /**
     * Description: https://eduinf.waw.pl/inf/prg/010_uc/7431.php
     */
    public IC74LS31() {
        super();
        this.inputs = new HashMap<>();
        this.outputs = new HashMap<>();
        this.observers = new HashMap<>();

        Integer[] inputPinNumbers = new Integer[] { 1, 3, 5, 6, 10, 11, 13, 15 };
        Integer[] outputPinNumbers = new Integer[] { 2, 4, 7, 9, 12, 14 };

        for (Integer input : inputPinNumbers) {
            inputs.put(input, new ComponentPin());
        }

        for (Integer output : outputPinNumbers) {
            outputs.put(output, new ComponentPin());
        }

        this.componentClass = ComponentClass.IC;
        this.humanName = this.getClass().getSimpleName() + "_" + getGlobalId();
        this.behaviour = ComponentBehaviour.UNLOCK;
        this.type = ComponentType.COMPONENT_74LS31;
    }
}

package edu.uj.po.simulation.circuits;

import java.util.HashMap;

import edu.uj.po.simulation.consts.ComponentType;
import edu.uj.po.simulation.enums.ComponentBehaviour;
import edu.uj.po.simulation.enums.ComponentClass;
import edu.uj.po.simulation.pins.ComponentPin;

/**
 * Description: https://eduinf.waw.pl/inf/prg/010_uc/7420.php
 */
public class IC74HC20 extends BaseComponent {
    public IC74HC20() {
        super();
        this.inputs = new HashMap<>();
        this.outputs = new HashMap<>();
        this.observers = new HashMap<>();

        Integer[] inputPinNumbers = new Integer[] { 1, 2, 4, 5, 9, 10, 12, 13};
        Integer[] outputPinNumbers = new Integer[] { 6, 8 };

        for (Integer input : inputPinNumbers) {
            inputs.put(input, new ComponentPin());
        }

        for (Integer output : outputPinNumbers) {
            outputs.put(output, new ComponentPin());
        }

        this.componentClass = ComponentClass.IC;
        this.humanName = this.getClass().getSimpleName() + "_" + getGlobalId();
        this.behaviour = ComponentBehaviour.UNLOCK;
        this.type = ComponentType.COMPONENT_74HC20;
    }
}

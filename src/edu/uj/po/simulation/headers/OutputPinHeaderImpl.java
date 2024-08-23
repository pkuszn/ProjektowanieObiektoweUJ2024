package edu.uj.po.simulation.headers;

import edu.uj.po.simulation.circuits.BaseComponent;
import edu.uj.po.simulation.consts.ComponentType;
import edu.uj.po.simulation.enums.ComponentBehaviour;
import edu.uj.po.simulation.enums.ComponentClass;
import edu.uj.po.simulation.pins.ComponentPin;
import java.util.HashMap;

public class OutputPinHeaderImpl extends BaseComponent {
    public OutputPinHeaderImpl(int size) {
        super();
        this.outputs = new HashMap<>();
        this.observers = new HashMap<>();
        this.componentClass = ComponentClass.OUT;
        for (int i = 1; i <= size; i++) {
            outputs.put(i, new ComponentPin());
        }
        this.humanName = getClass().getSimpleName() + "_" + getGlobalId();
        this.behaviour = ComponentBehaviour.UNLOCK;
        this.type = ComponentType.COMPONENT_OUTPUT_HEADER;
    }
}
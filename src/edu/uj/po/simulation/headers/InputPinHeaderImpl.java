package edu.uj.po.simulation.headers;

import edu.uj.po.simulation.circuits.BaseComponent;
import edu.uj.po.simulation.consts.ComponentType;
import edu.uj.po.simulation.enums.ComponentBehaviour;
import edu.uj.po.simulation.enums.ComponentClass;
import edu.uj.po.simulation.pins.ComponentPin;
import java.util.HashMap;

public class InputPinHeaderImpl extends BaseComponent {
    public InputPinHeaderImpl(int size) {
        super();
        this.inputs = new HashMap<>();
        this.observers = new HashMap<>();
        this.componentClass = ComponentClass.IN;
        for (int i = 1; i <= size; i++) {
            inputs.put(i, new ComponentPin());
        }
        this.humanName = this.getClass().getSimpleName() + "_" + getGlobalId();
        this.behaviour = ComponentBehaviour.UNLOCK;
        this.type = ComponentType.COMPONENT_INPUT_HEADER;
    }
}

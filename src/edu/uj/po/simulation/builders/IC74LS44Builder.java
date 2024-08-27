package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.ComponentBuilder;
import edu.uj.po.simulation.commands.IC74LS44Command;
import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.ComponentType;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.components.IC74LS44;
import java.util.HashMap;
import java.util.Map;

public class IC74LS44Builder implements ComponentBuilder {
    private IC74LS44 component;

    /**
     * Pin instrunction for 74LS44 component
     * is in [docs/housings/74LS44.png]
     */
    public IC74LS44Builder() {
        super();
    }

    @Override
    public void defineLogic() {
        component.setCommand(new IC74LS44Command());
    }

    @Override
    public void definePins() {
        Map<Integer, ComponentPin> pins = new HashMap<>();

        Integer[] inputPinNumbers = new Integer[] { 15, 14, 13, 12 };
        Integer[] outputPinNumbers = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 9, 10, 11 };

        for (Integer input : inputPinNumbers) {
            pins.put(input, new ComponentPin(input, PinType.IN, component.getGlobalId()));
        }

        for (Integer output : outputPinNumbers) {
            pins.put(output, new ComponentPin(output, PinType.OUT, component.getGlobalId()));
        }

        component.setPins(pins);
    }

    @Override
    public IC74LS44 buildComponent() {
        return component;
    }

    @Override
    public void defineComponent() {
        component = new IC74LS44();
    }

    @Override
    public void defineProperties() {
        component.setComponentClass(ComponentClass.IC);
        component.setType(ComponentType.COMPONENT_74LS44);
    }
}

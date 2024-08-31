package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.ComponentBuilder;
import edu.uj.po.simulation.commands.IC74152Command;
import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.ComponentType;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.components.IC74152;
import java.util.HashMap;
import java.util.Map;

public class IC74152Builder implements ComponentBuilder {
    private IC74152 component;

    /**
     * Pin instrunction for 74152 component
     * is in [docs/housings/74152.png]
     */
    public IC74152Builder() {
        super();
    }

    @Override
    public void defineLogic() {
        component.setCommand(new IC74152Command());
    }

    @Override
    public void definePins() {
        Map<Integer, ComponentPin> pins = new HashMap<>();

        Integer[] inputPinNumbers = new Integer[] { 5, 4, 3, 2, 1, 13, 12, 11, 10, 9, 8};
        Integer[] outputPinNumbers = new Integer[] { 6 };

        for (Integer input : inputPinNumbers) {
            pins.put(input, new ComponentPin(input, PinType.IN, component.getGlobalId()));
        }

        for (Integer output : outputPinNumbers) {
            pins.put(output, new ComponentPin(output, PinType.OUT, component.getGlobalId()));
        }

        component.setPins(pins);
    }

    @Override
    public IC74152 buildComponent() {
        return component;
    }

    @Override
    public void defineComponent() {
        component = new IC74152();
    }

    @Override
    public void defineProperties() {
        component.setComponentClass(ComponentClass.IC);
        component.setType(ComponentType.COMPONENT_74152);
    }
}

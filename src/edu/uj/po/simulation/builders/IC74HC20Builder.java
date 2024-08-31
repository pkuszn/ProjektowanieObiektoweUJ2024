package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.ComponentBuilder;
import edu.uj.po.simulation.commands.IC74HC20Command;
import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.ComponentType;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.components.IC74HC20;
import java.util.HashMap;
import java.util.Map;

public class IC74HC20Builder implements ComponentBuilder {
    private IC74HC20 component;

    /**
     * Pin instrunction for 74HC20 component
     * is in [docs/housings/74HC20.png]
     */
    public IC74HC20Builder() {
        super();
    }

    @Override
    public void defineComponent() {
        component = new IC74HC20();
    }

    @Override
    public void defineLogic() {
        component.setCommand(new IC74HC20Command());
    }

    @Override
    public IC74HC20 buildComponent() {
        return component;
    }

    @Override
    public void definePins() {
        Map<Integer, ComponentPin> pins = new HashMap<>();

        Integer[] inputPinNumbers = new Integer[] { 1, 2, 4, 5, 9, 10, 12, 13 };
        Integer[] outputPinNumbers = new Integer[] { 6, 8 };

        for (Integer input : inputPinNumbers) {
            pins.put(input, new ComponentPin(input, PinType.IN, component.getGlobalId()));
        }

        for (Integer output : outputPinNumbers) {
            pins.put(output, new ComponentPin(output, PinType.OUT, component.getGlobalId()));
        }

        component.setPins(pins);
    }

    @Override
    public void defineProperties() {
        component.setComponentClass(ComponentClass.IC);
        component.setType(ComponentType.COMPONENT_74HC20);
    }
}

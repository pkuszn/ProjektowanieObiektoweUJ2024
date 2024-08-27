package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.ComponentBuilder;
import edu.uj.po.simulation.commands.IC74LS31Command;
import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.ComponentType;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.components.IC74LS31;
import java.util.HashMap;
import java.util.Map;

public class IC74LS31Builder implements ComponentBuilder {
    private IC74LS31 component;

    /**
     * Pin instrunction for 74LS31 component
     * is in [docs/housings/74LS31.png]
     */
    public IC74LS31Builder() {
        super();
    }

    @Override
    public void defineComponent() {
        component = new IC74LS31();
    }

    @Override
    public void defineLogic() {
        component.setCommand(new IC74LS31Command());
    }
    @Override
    public IC74LS31 buildComponent() {
        return component;
    }

    @Override
    public void definePins() {
        Map<Integer, ComponentPin> pins = new HashMap<>();

        Integer[] inputPinNumbers = new Integer[] { 1, 3, 5, 6, 10, 11, 13, 15 };
        Integer[] outputPinNumbers = new Integer[] { 2, 4, 7, 9, 12, 14 };

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
        component.setType(ComponentType.COMPONENT_74LS31);
    }
}
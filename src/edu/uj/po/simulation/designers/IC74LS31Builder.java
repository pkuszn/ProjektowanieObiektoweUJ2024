package edu.uj.po.simulation.designers;

import java.util.HashMap;
import java.util.Map;

import edu.uj.po.simulation.abstractions.ComponentBuilder;
import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.ComponentType;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.components.IC74LS31;

public class IC74LS31Builder implements ComponentBuilder {
    private IC74LS31 component;

    /**
     * Pin instrunction for 74LS31 component
     * is in [/housings/74LS31.png]
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
        // TODO: Define logic for this component
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
            pins.put(input, new ComponentPin(input, PinType.IN));
        }

        for (Integer output : outputPinNumbers) {
            pins.put(output, new ComponentPin(output, PinType.OUT));
        }

        component.setPins(pins);
    }

    @Override
    public void defineProperties() {
        component.setComponentClass(ComponentClass.IC);
        component.setType(ComponentType.COMPONENT_74LS31);
    }
}

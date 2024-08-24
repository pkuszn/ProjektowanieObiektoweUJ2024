package edu.uj.po.simulation.designers;

import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.ComponentType;
import edu.uj.po.simulation.consts.PinType;

import java.util.HashMap;
import java.util.Map;

import edu.uj.po.simulation.abstractions.ComponentBuilder;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.circuits.IC74HC20;

public class IC74HC20Builder implements ComponentBuilder {
    private IC74HC20 component;

    /**
     * Pin instrunction for 74HC20 component
     * is in [/housings/74HC20.png]
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

    }

    @Override
    public IC74HC20 buildComponent() {
        return component;
    }

    @Override
    public void definePins() {
        Map<Integer, ComponentPin> pins = new HashMap<>();

        Integer[] inputPinNumbers = new Integer[] { 1, 2, 3, 4, 5, 9, 10, 11, 13 };
        Integer[] outputPinNumbers = new Integer[] { 6, 8, 12 };

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
        component.setType(ComponentType.COMPONENT_74HC20);
    }
}

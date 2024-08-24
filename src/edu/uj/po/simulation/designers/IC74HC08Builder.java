package edu.uj.po.simulation.designers;

import java.util.HashMap;
import java.util.Map;

import edu.uj.po.simulation.abstractions.ComponentBuilder;
import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.ComponentType;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.components.IC74HC08;

public class IC74HC08Builder implements ComponentBuilder {
    private IC74HC08 component;

    /**
     * Pin instrunction for 74HC08 component
     * is in [/housings/74HC08.png]
     */
    public IC74HC08Builder() {
        super();
    }

    @Override
    public void defineComponent() {
        this.component = new IC74HC08();
    }

    @Override
    public void defineLogic() {
        // TODO: Define logic for this component
    }

    @Override
    public IC74HC08 buildComponent() {
        return component;
    }

    @Override
    public void definePins() {
        Map<Integer, ComponentPin> pins = new HashMap<>();
        Integer[] inputPinNumbers = new Integer[] { 1, 2, 4, 5, 9, 10, 12, 13 };
        Integer[] outputPinNumbers = new Integer[] { 3, 6, 8, 11 };

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
        component.setType(ComponentType.COMPONENT_74HC08);
    }
}

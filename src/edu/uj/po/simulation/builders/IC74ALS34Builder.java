package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.ComponentBuilder;
import edu.uj.po.simulation.commands.IC74ALS34Command;
import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.ComponentType;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.components.IC74ALS34;
import java.util.HashMap;
import java.util.Map;

public class IC74ALS34Builder implements ComponentBuilder  {
    private IC74ALS34 component;
    /**
     * Pin instrunction for 74ALS34 component
     * is in [docs/housings/74ALS34.png]
     */
    public IC74ALS34Builder() {
        super();
    }
    
    @Override
    public void defineComponent() {
        component = new IC74ALS34();
    }

    @Override
    public void defineLogic() {
        component.setCommand(new IC74ALS34Command());
    }

    @Override
    public void definePins() {
        Map<Integer, ComponentPin> pins = new HashMap<>();
        Integer[] inputPinNumbers = new Integer[] { 1, 3, 5, 9, 11, 13 };
        Integer[] outputPinNumbers = new Integer[] { 2, 4, 6, 7, 10, 12 };

        for (Integer input : inputPinNumbers) {
            pins.put(input, new ComponentPin(input, PinType.IN, component.getGlobalId()));
        }

        for (Integer output : outputPinNumbers) {
            pins.put(output, new ComponentPin(output, PinType.OUT, component.getGlobalId()));
        }

        component.setPins(pins);
    }

    @Override
    public IC74ALS34 buildComponent() {
        return component;
    }

    @Override
    public void defineProperties() {
        component.setComponentClass(ComponentClass.IC);
        component.setType(ComponentType.COMPONENT_74HALS34);
    }
}

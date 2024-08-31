package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.ComponentBuilder;
import edu.uj.po.simulation.commands.IC7482Command;
import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.ComponentType;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.components.IC7482;
import java.util.HashMap;
import java.util.Map;

public class IC7482Builder implements ComponentBuilder {
    private IC7482 component;

    /**
     * Pin instrunction for 7482 component
     * is in [docs/housings/7482.png]
     */
    public IC7482Builder() {
        super();
    }

    @Override
    public void defineLogic() {
        component.setCommand(new IC7482Command());
    }

    @Override
    public void definePins() {
        Map<Integer, ComponentPin> pins = new HashMap<>();

        Integer[] inputPinNumbers = new Integer[] { 14, 13, 5, 3, 2 };
        Integer[] outputPinNumbers = new Integer[] { 12, 10, 1 };

        for (Integer input : inputPinNumbers) {
            pins.put(input, new ComponentPin(input, PinType.IN, component.getGlobalId()));
        }

        for (Integer output : outputPinNumbers) {
            pins.put(output, new ComponentPin(output, PinType.OUT, component.getGlobalId()));
        }

        component.setPins(pins);
    }

    @Override
    public IC7482 buildComponent() {
        return component;
    }

    @Override
    public void defineComponent() {
        component = new IC7482();
    }

    @Override
    public void defineProperties() {
        component.setComponentClass(ComponentClass.IC);
        component.setType(ComponentType.COMPONENT_7482);
    }
}

package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.ComponentBuilder;
import edu.uj.po.simulation.commands.IC74HC02Command;
import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.ComponentType;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.components.IC74HC02;
import java.util.HashMap;

public class IC74HC02Builder implements ComponentBuilder {
    private IC74HC02 component;

    /**
     * Pin instrunction for 74HC02 component
     * is in [docs/housings/74HC02.png]
     */
    public IC74HC02Builder() {
        super();
    }

    @Override
    public void defineComponent() {
        component = new IC74HC02();
    }

    @Override
    public void defineLogic() {
        component.setCommand(new IC74HC02Command());
    }
    
    @Override
    public IC74HC02 buildComponent() {
        return component;
    }

    @Override
    public void definePins() {
        HashMap<Integer, ComponentPin> pins = new HashMap<>();
        Integer[] inputPinNumbers = new Integer[] { 2, 3, 5, 6, 8, 9, 11, 12 };
        Integer[] outputPinNumbers = new Integer[] { 1, 4, 10, 13 };

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
        component.setType(ComponentType.COMPONENT_74HC02);
        component.setComponentClass(ComponentClass.IC);
    }
}

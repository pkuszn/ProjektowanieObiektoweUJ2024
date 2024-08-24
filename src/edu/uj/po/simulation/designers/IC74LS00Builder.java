package edu.uj.po.simulation.designers;

import java.util.HashMap;
import java.util.Map;

import edu.uj.po.simulation.abstractions.ComponentBuilder;
import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.ComponentType;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.designers.creators.NandGateCreator;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.circuits.IC74LS00;

public class IC74LS00Builder implements ComponentBuilder {
    private IC74LS00 component;

    /**
     * Pin instrunction for 74LS00 component
     * is in [/housings/74LS00.png]
     */
    public IC74LS00Builder() {
        super();
    }

    @Override
    public void defineComponent() {
        component = new IC74LS00();
    }

    @Override
    public void defineLogic() {
        NandGateCreator andGateCreator = new NandGateCreator();
    }

    @Override
    public IC74LS00 buildComponent() {
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
        component.setType(ComponentType.COMPONENT_74LS00);
    }
}

package edu.uj.po.simulation.designers;

import edu.uj.po.simulation.abstractions.HeaderBuilder;
import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.ComponentType;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.headers.InputPinHeader;
import java.util.HashMap;
import java.util.Map;

public class InputHeaderBuilder implements HeaderBuilder {
    private InputPinHeader component;
    private int size;

    @Override
    public void defineComponent() {
        component = new InputPinHeader();
    }

    @Override
    public void definePins() {
        Map<Integer, ComponentPin> pins = new HashMap<>();
        for (int i = 1; i <= size; i++) {
            pins.put(i, new ComponentPin(i, PinType.IN));
        }
        component.setPins(pins);
    }

    @Override
    public void defineProperties() {
        component.setType(ComponentType.COMPONENT_INPUT_HEADER);
        component.setComponentClass(ComponentClass.IN);
    }

    @Override
    public InputPinHeader buildComponent() {
        return component;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }
}

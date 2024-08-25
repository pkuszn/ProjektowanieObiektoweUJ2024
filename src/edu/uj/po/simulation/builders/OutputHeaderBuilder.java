package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.HeaderBuilder;
import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.consts.ComponentType;
import edu.uj.po.simulation.consts.PinType;
import edu.uj.po.simulation.models.ComponentPin;
import edu.uj.po.simulation.models.headers.OutputPinHeader;
import java.util.HashMap;
import java.util.Map;

public class OutputHeaderBuilder implements HeaderBuilder {
    private OutputPinHeader component;
    private int size;

    @Override
    public void defineComponent() {
        component = new OutputPinHeader();
    }

    @Override
    public void definePins() {
        Map<Integer, ComponentPin> pins = new HashMap<>();
        for (int i = 1; i <= this.size; i++) {
            pins.put(i, new ComponentPin(i, PinType.OUT, component.getGlobalId()));
        }
        component.setPins(pins);
    }

    @Override
    public void defineProperties() {
        component.setType(ComponentType.COMPONENT_OUTPUT_HEADER);
        component.setComponentClass(ComponentClass.OUT);
    }

    @Override
    public Component buildComponent() {
        return component;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }
}

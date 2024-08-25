package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentBuilder;
import edu.uj.po.simulation.abstractions.Director;
import edu.uj.po.simulation.abstractions.HeaderBuilder;

public class ComponentDirector implements Director {

    @Override
    public Component make(ComponentBuilder builder) {
        builder.defineComponent();
        builder.defineLogic();
        builder.definePins();
        builder.defineProperties();
        return builder.buildComponent();
    }

    @Override
    public Component make(HeaderBuilder builder, int size) {
        builder.setSize(size);
        builder.defineComponent();
        builder.definePins();
        builder.defineProperties();
        return builder.buildComponent();
    }
}

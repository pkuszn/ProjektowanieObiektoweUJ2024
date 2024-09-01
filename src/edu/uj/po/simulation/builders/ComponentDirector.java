package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.Component;
import edu.uj.po.simulation.abstractions.ComponentBuilder;
import edu.uj.po.simulation.abstractions.Director;
import edu.uj.po.simulation.abstractions.HeaderBuilder;
import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.interfaces.UnknownChip;
import java.util.HashMap;

public class ComponentDirector implements Director {
    private final HashMap<Integer, ComponentBuilder> builders;

    public ComponentDirector() {
        super();
        this.builders = new HashMap<>();
        this.builders.put(7400, new IC74LS00Builder());
        this.builders.put(7402, new IC74HC02Builder());
        this.builders.put(7404, new IC74HC04Builder());
        this.builders.put(7408, new IC74HC08Builder());
        this.builders.put(7410, new IC74HC10Builder());
        this.builders.put(7411, new IC74HC11Builder());
        this.builders.put(7420, new IC74HC20Builder());
        this.builders.put(7431, new IC74LS31Builder());
        this.builders.put(7432, new IC74LS32Builder());
        this.builders.put(7434, new IC74ALS34Builder());
        this.builders.put(7442, new IC74LS42Builder());
        this.builders.put(7444, new IC74LS44Builder());
        this.builders.put(7482, new IC7482Builder());
        this.builders.put(74138, new IC74138Builder());
        this.builders.put(74152, new IC74152Builder());
    }

    @Override
    public Component orderComponentBuild(int code) throws UnknownChip{
        ComponentBuilder builder = this.builders.get(code);
        if (builder == null) {
            throw new UnknownChip();
        }

        builder.defineComponent();
        builder.defineLogic();
        builder.definePins();
        builder.defineProperties();
        return builder.buildComponent();
    }

    @Override
    public Component orderHeaderBuild(ComponentClass className, int size) {
        HeaderBuilder builder;

        if (className.equals(ComponentClass.IN)) {
            builder = new InputHeaderBuilder();
        } else {
            builder = new OutputHeaderBuilder();
        }

        builder.setSize(size);
        builder.defineComponent();
        builder.definePins();
        builder.defineProperties();
        return builder.buildComponent();
    }
}

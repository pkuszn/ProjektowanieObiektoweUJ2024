package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.ComponentBuilder;
import edu.uj.po.simulation.commands.IC74LS44Command;
import edu.uj.po.simulation.models.components.IC74LS44;

public class IC74LS44Builder implements ComponentBuilder {
    private IC74LS44 component;

    /**
     * Pin instrunction for 74LS44 component
     * is in [docs/housings/74LS44.png]
     */
    public IC74LS44Builder() {
        super();
    }

    @Override
    public void defineLogic() {
        component.setCommand(new IC74LS44Command());
    }

    @Override
    public void definePins() {
    }

    @Override
    public IC74LS44 buildComponent() {
        return component;
    }

    @Override
    public void defineComponent() {
        component = new IC74LS44();
    }

    @Override
    public void defineProperties() {
    }

}

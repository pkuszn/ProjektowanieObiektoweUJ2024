package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.ComponentBuilder;
import edu.uj.po.simulation.commands.IC74LS42Command;
import edu.uj.po.simulation.models.components.IC74LS42;

public class IC74LS42Builder implements ComponentBuilder {
    private IC74LS42 component;

    /**
     * Pin instrunction for 74LS42 component
     * is in [docs/housings/74LS42.png]
     */
    public IC74LS42Builder() {
        super();
    }

    @Override
    public void defineLogic() {
        component.setCommand(new IC74LS42Command());
    }

    @Override
    public void definePins() {
        throw new UnsupportedOperationException("Unimplemented method 'connectGates'");
    }
    
    @Override
    public IC74LS42 buildComponent() {
        return component;
    }

    @Override
    public void defineComponent() {
        component = new IC74LS42();
    }

    @Override
    public void defineProperties() {
        throw new UnsupportedOperationException("Unimplemented method 'defineProperties'");
    }
    
}

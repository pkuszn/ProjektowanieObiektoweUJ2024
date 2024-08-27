package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.ComponentBuilder;
import edu.uj.po.simulation.commands.IC74138Command;
import edu.uj.po.simulation.models.components.IC74138;

public class IC74138Builder implements ComponentBuilder {
    private IC74138 component;

    /**
     * Pin instrunction for 74138 component
     * is in [docs/housings/74138.png]
     */
    public IC74138Builder() {
        super();
    }

    @Override
    public void defineLogic() {
        component.setCommand(new IC74138Command());
    }

    @Override
    public void definePins() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'connectGates'");
    }

    @Override
    public IC74138 buildComponent() {
        return component;
    }

    @Override
    public void defineComponent() {
        component = new IC74138();
    }

    @Override
    public void defineProperties() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'defineProperties'");
    }
    
}

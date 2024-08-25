package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.ComponentBuilder;
import edu.uj.po.simulation.commands.IC74152Command;
import edu.uj.po.simulation.models.components.IC74152;

public class IC74152Builder implements ComponentBuilder {
    private IC74152 component;
    public IC74152Builder() {
        super();
    }

    @Override
    public void defineLogic() {
        component.setCommand(new IC74152Command());
    }

    @Override
    public void definePins() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'connectGates'");
    }

    @Override
    public IC74152 buildComponent() {
        return component;
    }

    @Override
    public void defineComponent() {
        component = new IC74152();
    }

    @Override
    public void defineProperties() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'defineProperties'");
    }
    
}

package edu.uj.po.simulation.builders;

import edu.uj.po.simulation.abstractions.ComponentBuilder;
import edu.uj.po.simulation.commands.IC7482Command;
import edu.uj.po.simulation.models.components.IC7482;

public class IC7482Builder implements ComponentBuilder {
    private IC7482 component;

    /**
     * Pin instrunction for 7482 component
     * is in [docs/housings/7482.png]
     */
    public IC7482Builder() {
        super();
    }

    @Override
    public void defineLogic() {
        component.setCommand(new IC7482Command());
    }

    @Override
    public void definePins() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'connectGates'");
    }

    @Override
    public IC7482 buildComponent() {
        return component;
    }

    @Override
    public void defineComponent() {
        component = new IC7482();
    }

    @Override
    public void defineProperties() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'defineProperties'");
    }

}

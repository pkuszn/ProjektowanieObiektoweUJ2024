package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.builders.IC74LS44Builder;
import edu.uj.po.simulation.models.components.IC74LS44;

public class Test_IC74LS44 extends TestBase {
    private final IC74LS44 component;

    public Test_IC74LS44() {
        super();
        this.component = (IC74LS44) this.director.make(new IC74LS44Builder());
    }

    @Override
    protected void testComponent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testComponent'");
    }
}

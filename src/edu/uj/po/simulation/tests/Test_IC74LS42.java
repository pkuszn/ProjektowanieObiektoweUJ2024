package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.builders.IC74LS42Builder;
import edu.uj.po.simulation.models.components.IC74LS42;

public class Test_IC74LS42 extends TestBase {
    private IC74LS42 component;

    public Test_IC74LS42() {
        super();
        this.component = (IC74LS42) this.director.make(new IC74LS42Builder());
    }

    @Override
    protected void testComponent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testComponent'");
    }
}

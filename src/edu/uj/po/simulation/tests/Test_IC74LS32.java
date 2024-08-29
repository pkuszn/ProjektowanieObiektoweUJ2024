package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.builders.IC74LS32Builder;
import edu.uj.po.simulation.models.components.IC74LS32;

public class Test_IC74LS32 extends TestBase {
    private final IC74LS32 component;

    public Test_IC74LS32() {
        super();
        this.component = (IC74LS32) this.director.make(new IC74LS32Builder());
    }

    @Override
    protected void testComponent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testComponent'");
    }
}

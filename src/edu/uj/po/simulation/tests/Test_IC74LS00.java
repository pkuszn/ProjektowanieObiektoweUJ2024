package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.builders.IC74LS00Builder;
import edu.uj.po.simulation.models.components.IC74LS00;

public class Test_IC74LS00 extends TestBase {
    private final IC74LS00 component;
    public Test_IC74LS00() {
        super();
        this.component = (IC74LS00) this.director.make(new IC74LS00Builder());
    }
    @Override
    protected void testComponent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testComponent'");
    }
}

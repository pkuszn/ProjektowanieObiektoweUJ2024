package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.builders.IC74LS31Builder;
import edu.uj.po.simulation.models.components.IC74LS31;

public class Test_IC74LS31 extends TestBase {
    private final IC74LS31 component;

    public Test_IC74LS31() {
        super();
        this.component = (IC74LS31) this.director.make(new IC74LS31Builder());
    }

    @Override
    protected void testComponent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testComponent'");
    }
}

package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.builders.IC74138Builder;
import edu.uj.po.simulation.models.components.IC74138;

public class Test_IC74138 extends TestBase {
    private final IC74138 component;

    public Test_IC74138() {
        super();
        this.component = (IC74138) this.director.make(new IC74138Builder());
    }

    @Override
    protected void testComponent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testComponent'");
    }
}

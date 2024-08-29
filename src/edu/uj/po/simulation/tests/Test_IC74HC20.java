package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.builders.IC74HC20Builder;
import edu.uj.po.simulation.models.components.IC74HC20;

public class Test_IC74HC20 extends TestBase {
    private final IC74HC20 component;

    public Test_IC74HC20() {
        super();
        this.component = (IC74HC20) this.director.make(new IC74HC20Builder());
    }

    @Override
    protected void testComponent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testComponent'");
    }
}

package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.builders.IC74HC11Builder;
import edu.uj.po.simulation.models.components.IC74HC11;

public class Test_IC74HC11 extends TestBase {
    private final IC74HC11 component;

    public Test_IC74HC11() {
        super();
        this.component = (IC74HC11) this.director.make(new IC74HC11Builder());
    }

    @Override
    protected void testComponent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testComponent'");
    }
}

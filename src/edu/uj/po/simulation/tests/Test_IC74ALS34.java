package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.builders.IC74ALS34Builder;
import edu.uj.po.simulation.models.components.IC74ALS34;

public class Test_IC74ALS34 extends TestBase{
    private IC74ALS34 component;
    public Test_IC74ALS34() {
        super();
        this.component = (IC74ALS34) this.director.make(new IC74ALS34Builder());
    }
    @Override
    protected void testComponent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testComponent'");
    }
}

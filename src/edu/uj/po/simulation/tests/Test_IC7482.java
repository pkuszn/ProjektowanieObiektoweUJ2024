package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.builders.IC7482Builder;
import edu.uj.po.simulation.models.components.IC7482;

public class Test_IC7482 extends TestBase {
    private final IC7482 component;

    public Test_IC7482() {
        super();
        this.component = (IC7482) this.director.make(new IC7482Builder());
    }

    @Override
    protected void testComponent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testComponent'");
    }
}

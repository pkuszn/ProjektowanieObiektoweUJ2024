package edu.uj.po.simulation.tests;

import edu.uj.po.simulation.builders.IC74152Builder;
import edu.uj.po.simulation.models.components.IC74152;

public class Test_IC74152 extends TestBase {
    private final IC74152 component;
    public Test_IC74152() {
        super();
        this.component = (IC74152) this.director.make(new IC74152Builder());
    }
    @Override
    protected void testComponent() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'testComponent'");
    }
}

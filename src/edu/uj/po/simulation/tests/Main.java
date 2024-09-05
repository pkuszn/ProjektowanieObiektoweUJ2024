package edu.uj.po.simulation.tests;

public class Main {
    // TODO: Do not trust because there is no test framework so the object's instances are not properly handled
    public static void main(String[] args) throws Exception {
        new Test_IC74LS31().testComponent();
        new Test_IC74HC02().testComponent();
        new Test_IC74HC04().testComponent();
        new Test_IC74HC08().testComponent();
        new Test_IC74HC10().testComponent();
        new Test_IC74HC11().testComponent();
        new Test_IC74HC20().testComponent();
        new Test_IC74LS00().testComponent();
        new Test_IC74LS32().testComponent();
        new Test_IC74ALS34().testComponent();
        new Test_IC74LS42().testComponent();
        new Test_IC74LS44().testComponent();
        new Test_IC7482().testComponent();
        new Test_IC74138().testComponent();
        new Test_IC74152().testComponent();
        new Test_BuildingComponents().testComponents();
        new Test_StationaryState().testComponents();
        new Test_Simulation().testComponents();
        new Test_Optimization().testComponents();
    }
}

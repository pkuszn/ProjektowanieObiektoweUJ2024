package edu.uj.po.simulation.abstractions;

public interface Builder {
    void defineComponent();
    void definePins();
    void defineProperties();
    Component buildComponent();
}

package edu.uj.po.simulation.abstractions;

public interface Director {
    Component make(ComponentBuilder builder);
    Component make(HeaderBuilder builder, int size);
}

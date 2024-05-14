package edu.uj.po.simulation.interfaces;

public interface Observer<T> {
    void update(T newState);
}

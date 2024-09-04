package edu.uj.po.simulation.abstractions;

public interface ComponentCommand {
    void execute(Component component);
    void executeTick(Component component);
}

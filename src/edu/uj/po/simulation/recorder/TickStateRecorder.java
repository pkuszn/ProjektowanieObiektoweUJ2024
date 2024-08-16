package edu.uj.po.simulation.recorder;

import java.util.List;

public interface TickStateRecorder {
    void addTickStates(List<TickState> tickStates);
    void addTickState(TickState tickState);
    void save(int ticks, SessionType sessionType);
}

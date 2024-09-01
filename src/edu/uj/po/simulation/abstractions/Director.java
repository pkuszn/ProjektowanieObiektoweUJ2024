package edu.uj.po.simulation.abstractions;

import edu.uj.po.simulation.consts.ComponentClass;
import edu.uj.po.simulation.interfaces.UnknownChip;

public interface Director {
    Component orderComponentBuild(int code) throws UnknownChip;
    Component orderHeaderBuild(ComponentClass className, int size);
}

package edu.uj.po.simulation.headers;

import edu.uj.po.simulation.abstractions.OutputPinHeader;
import edu.uj.po.simulation.circuits.BaseComponent;
import edu.uj.po.simulation.consts.ComponentType;
import edu.uj.po.simulation.enums.ComponentBehaviour;
import edu.uj.po.simulation.enums.ComponentClass;
import edu.uj.po.simulation.interfaces.ComponentPinState;
import edu.uj.po.simulation.pins.ComponentPin;
import edu.uj.po.simulation.recorder.ComponentState;
import edu.uj.po.simulation.utils.ComponentLogger;
import edu.uj.po.simulation.utils.PinStateMapper;
import java.time.LocalDateTime;
import java.util.HashMap;

public class OutputPinHeaderImpl extends BaseComponent implements OutputPinHeader {
    public OutputPinHeaderImpl(int size) {
        super();
        this.outputs = new HashMap<>();
        this.observers = new HashMap<>();
        this.componentClass = ComponentClass.OUT;
        for (int i = 1; i <= size; i++) {
            outputs.put(i, new ComponentPin());
        }
        this.humanName = getClass().getSimpleName() + "_" + getGlobalId();
        this.behaviour = ComponentBehaviour.UNLOCK;
        this.type = ComponentType.COMPONENT_OUTPUT_HEADER;
    }
    
    @Override
    public void setState(ComponentPinState state) {
        while (true) {
            if (getBehaviour() == ComponentBehaviour.UNLOCK) {
                System.out.println(this.globalId +  " unlock");
                ComponentLogger.logPinState(state.componentId(), state.pinId(), PinStateMapper.toBoolean(state.state()));
                ComponentState componentState = new ComponentState(
                    state.pinId(), 
                    humanName, 
                    type, 
                    (ComponentClass) componentClass, 
                    state.pinId(), 
                    tick, 
                    state.state(), 
                    LocalDateTime.now());
                this.addComponentState(this.globalId, componentState);
                break;
            }
        }
    }
}
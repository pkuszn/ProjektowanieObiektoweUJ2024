package edu.uj.po.simulation.headers;

import edu.uj.po.simulation.abstractions.InputPinHeader;
import edu.uj.po.simulation.circuits.BaseComponent;
import edu.uj.po.simulation.consts.ComponentType;
import edu.uj.po.simulation.enums.ComponentBehaviour;
import edu.uj.po.simulation.enums.ComponentClass;
import edu.uj.po.simulation.interfaces.UnknownPin;
import edu.uj.po.simulation.pins.ComponentPin;
import edu.uj.po.simulation.recorder.ComponentState;
import edu.uj.po.simulation.utils.PinStateMapper;
import java.time.LocalDateTime;
import java.util.HashMap;

public class InputPinHeaderImpl extends BaseComponent implements InputPinHeader {
    public InputPinHeaderImpl(int size) {
        super();
        this.inputs = new HashMap<>();
        this.observers = new HashMap<>();
        this.componentClass = ComponentClass.IN;
        for (int i = 1; i <= size; i++) {
            inputs.put(i, new ComponentPin());
        }
        this.humanName = this.getClass().getSimpleName() + "_" + getGlobalId();
        this.behaviour = ComponentBehaviour.UNLOCK;
        this.type = ComponentType.COMPONENT_INPUT_HEADER;
    }

    @Override
    public void setPinState(int pinNumber, boolean value) throws InterruptedException, UnknownPin {
        while (true) {
            if (getBehaviour() == ComponentBehaviour.UNLOCK) {
                System.out.println(this.globalId + " unlock");
                ComponentPin pin = inputs.get(pinNumber);
                if (pin == null) {
                    System.out.println("Pin not updated");
                }
                ComponentState componentState = new ComponentState(
                        pinNumber,
                        humanName,
                        type, 
                        (ComponentClass) componentClass,
                        pinNumber,
                        tick,
                        PinStateMapper.toPinState(value),
                        LocalDateTime.now());
                this.addComponentState(this.globalId, componentState);
                pin.setPin(value);
                notifyObserver(pinNumber);
                break;
            }
        }
    }
}

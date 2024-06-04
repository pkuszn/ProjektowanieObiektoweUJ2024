package edu.uj.po.simulation.utils;

import edu.uj.po.simulation.interfaces.PinState;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StateChangeNotifierImpl {
    private final List<String> journal;
    private static StateChangeNotifierImpl instance;
    private final String filePath = "./src/edu/uj/po/simulation/logs/componentLog.log";
    private final FileHandler fh;

    private StateChangeNotifierImpl() throws SecurityException, IOException {
        super();
        this.journal = new ArrayList<>();
        this.fh = new FileHandler(filePath);
    }

    public void notify(int componentId, PinState state) {
        LocalDateTime date = LocalDateTime.now();
        String message = date + " " + "componentId: " + componentId + " " + "state: " + state + "\n";
        journal.add(message);
    }

    public void notify(int componentId, int pinId, PinState state) {
        LocalDateTime date = LocalDateTime.now();
        String message = date + " " + "componentId: " + componentId + " " + "pinId: " + pinId + " " + "state: " + state + "\n";
        journal.add(message);
    }

    public static StateChangeNotifierImpl getInstance() throws SecurityException, IOException {
        if (instance == null) {
            instance = new StateChangeNotifierImpl();
        }
        return instance;
    }

    public void save() throws Exception {
        fh.writeAll(journal);
    }
}

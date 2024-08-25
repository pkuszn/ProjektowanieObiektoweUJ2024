package edu.uj.po.simulation.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import edu.uj.po.simulation.interfaces.ComponentPinState;


public class SimulationResultSaver {

    public static final String STATIC_GUID = generateStaticGuid();

    public static void saveResultToJson(Map<Integer, Set<ComponentPinState>> result) {
        String jsonString = mapToJson(result);
        String timestamp = getCurrentTimestamp();
        String guid = STATIC_GUID;

        File directory = new File("simulation_results/" + guid);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filename = String.format("%s/simulation_result_%s.json", directory.getPath(), timestamp);

        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(jsonString);
            System.out.println("Simulation results saved to file: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String mapToJson(Map<Integer, Set<ComponentPinState>> result) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (Map.Entry<Integer, Set<ComponentPinState>> entry : result.entrySet()) {
            int tick = entry.getKey();
            Set<ComponentPinState> states = entry.getValue();
            
            sb.append("\"").append(tick).append("\":[");
            for (ComponentPinState state : states) {
                sb.append(componentPinStateToJson(state)).append(",");
            }
            if (!states.isEmpty()) {
                sb.setLength(sb.length() - 1);
            }
            sb.append("],");
        }

        if (!result.isEmpty()) {
            sb.setLength(sb.length() - 1);
        }

        sb.append("}");
        return sb.toString();
    }

    private static String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
                .withZone(ZoneId.systemDefault());
        return formatter.format(Instant.now());
    }

    private static String generateStaticGuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    private static String componentPinStateToJson(ComponentPinState state) {
        return String.format(
            "{\"componentId\":%d, \"pinNumber\":%d, \"state\":\"%s\"}",
            state.componentId(),
            state.pinId(),
            state.state().name()
        );
    }
}

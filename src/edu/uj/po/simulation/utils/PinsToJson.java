package edu.uj.po.simulation.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import edu.uj.po.simulation.models.ComponentPin;

public class PinsToJson {
    public static void saveToJson(int componentId, Map<Integer, ComponentPin> map) {
        String jsonString = mapToJson(map);
        String timestamp = getCurrentTimestamp();
        String filename = String.format("pins_%d_%s.json", componentId, timestamp);
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(jsonString);
            System.out.println("Map serialized to JSON and saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
                .withZone(ZoneId.systemDefault());
        return formatter.format(Instant.now());
    }

    private static String mapToJson(Map<Integer, ComponentPin> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (Map.Entry<Integer, ComponentPin> entry : map.entrySet()) {
            sb.append("\"").append(entry.getKey()).append("\":")
                    .append(entry.getValue().toJson()).append(",");
        }

        if (!map.isEmpty()) {
            // Remove the last comma
            sb.setLength(sb.length() - 1);
        }

        sb.append("}");
        return sb.toString();
    }
}

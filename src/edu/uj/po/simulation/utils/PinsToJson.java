package edu.uj.po.simulation.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.UUID;

import edu.uj.po.simulation.models.ComponentPin;

public class PinsToJson {
    public static final String STATIC_GUID = generateStaticGuid();

    public static void saveToJson(int componentId, Map<Integer, ComponentPin> map) {
        String jsonString = mapToJson(map);
        String timestamp = getCurrentTimestamp();

        File directory = new File("json_files/" + STATIC_GUID);
        if (!directory.exists()) {
            directory.mkdirs(); 
        }

        String filename = String.format("%s/pins_%d_%s.json", directory.getPath(), componentId, timestamp);
        try (FileWriter fileWriter = new FileWriter(filename)) {
            fileWriter.write(jsonString);
            System.out.println("Map serialized to JSON and saved to file: " + filename);
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
            sb.setLength(sb.length() - 1);
        }

        sb.append("}");
        return sb.toString();
    }

    private static String generateStaticGuid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}

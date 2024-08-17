package edu.uj.po.simulation.recorder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class ComponentStateRecorder {
    private final Map<Integer, ComponentState> componentStateMap;
    private final String csvFileDir = "src/edu/uj/po/simulation/data";
    private static ComponentStateRecorder instance;

    private ComponentStateRecorder() {
        super();
        this.componentStateMap = new HashMap<>();
    }

    public static ComponentStateRecorder getInstance() {
        if (instance == null) {
            instance = new ComponentStateRecorder();
        }
        return instance;
    }

    public void addComponentStates(Map<Integer, ComponentState> componentStateMap) {
        if (componentStateMap != null && !componentStateMap.isEmpty()) {
            this.componentStateMap.putAll(componentStateMap);
        }
    }

    public void addComponentState(Integer globalId, ComponentState componentState) {
        if (componentState != null) {
            this.componentStateMap.put(globalId, componentState);
        }
    }

    public void save(SessionType sessionType) throws NullPointerException, IllegalStateException {
        if (componentStateMap == null) {
            throw new NullPointerException("Component state map is null.");
        }

        if (componentStateMap.isEmpty()) {
            throw new IllegalStateException("Component state map is empty.");
        }

        String recordAsCsv = componentStateMap.entrySet().stream()
                .map(entry -> convertSingleToCsv(entry.getValue()))
                .collect(Collectors.joining(System.getProperty("line.separator")));

        if (recordAsCsv.isEmpty()) {
            throw new IllegalStateException("No valid CSV data generated from the component states.");
        }

        saveToFile(recordAsCsv, sessionType);
    }

    private void saveToFile(String recordAsCsv, SessionType sessionType) {
        long timestampMillis = System.currentTimeMillis();
        String sessionTypeDir = Paths.get(csvFileDir, SessionTypeMapper.Map(sessionType) + timestampMillis + ".csv").toString();
        File directory = new File(sessionTypeDir).getParentFile();
        boolean fileExists = directory.exists();
        if (!fileExists) {
            directory.mkdirs(); 
        }

        try (FileWriter writer = new FileWriter(sessionTypeDir)) {
            String headers = "GlobalId,HumanName,Type,ComponentClass,PinNumber,TickNumber,PinState";
            writer.append(headers).append(System.lineSeparator());
            writer.append(recordAsCsv);
        } catch (IOException e) {
            System.out.println("Error during saving states to the CSV file: " + e.getMessage());
        }
    }

    private String convertSingleToCsv(ComponentState componentState) {
        if (componentState == null) {
            throw new IllegalArgumentException("ComponentState cannot be null.");
        }

        String csvRow = Stream.of(
                componentState.getGlobalId(),
                componentState.getHumanName(),
                componentState.getType(),
                componentState.getComponentClass(),
                componentState.getPinNumber(),
                componentState.getTickNumber(),
                componentState.getPinState())
                .map(value -> value != null ? value.toString().replace("\"", "\"\"") : "")
                .map(value -> value.contains(",") || value.contains("\"") ? "\"" + value + "\"" : value)
                .collect(Collectors.joining(","));

        return csvRow;
    }
}

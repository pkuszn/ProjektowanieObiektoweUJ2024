package edu.uj.po.simulation.recorder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TickStateRecorderImpl implements TickStateRecorder {
    private final List<TickState> tickStates;
    private final String csvFileDir = System.getProperty("user.dir") + "\\data\\";

    public TickStateRecorderImpl() {
        super();
        this.tickStates = new ArrayList<>();
    }

    @Override
    public void addTickStates(List<TickState> tickStates) {
        if (tickStates != null && !tickStates.isEmpty()) {
            this.tickStates.addAll(tickStates);
        }
    }

    @Override
    public void addTickState(TickState tickState) {
        if (tickState != null) {
            this.tickStates.add(tickState);
        }
    }

    @Override
    public void save(int ticks, SessionType sessionType) throws NullPointerException, IllegalStateException {
        if (tickStates == null) {
            throw new NullPointerException();
        } 

        if (tickStates.isEmpty()) {
            throw new IllegalStateException();
        }

        String recordAsCsv = tickStates.stream()
            .map(this::convertSingleToCsv)
            .collect(Collectors.joining(System.getProperty("line.separator")));

        if (recordAsCsv == null || recordAsCsv.isEmpty()) {
            //TODO...
        }


    }
    
    private void saveToFile(String recordAsCsv, SessionType sessionType) {
        String sessionTypeDir = csvFileDir + "\\" + SessionTypeMapper.Map(sessionType);
        try (FileWriter writer = new FileWriter(sessionTypeDir)) {
            writer.append(recordAsCsv);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String convertSingleToCsv(TickState tickState) {
        return Stream.of(tickState.getGlobalId(), tickState.getHumanName(), tickState.getType(), tickState.getComponentClass(), tickState.getTickNumber(), tickState.getPinNumber(), tickState.getDate())
            .map(value -> ((String) value).replaceAll("\"", "\"\""))
            .map(value -> Stream.of("\"", ",").anyMatch(value::contains) ? "\"" + value + "\"" : value)
            .collect(Collectors.joining(","));
    }
}

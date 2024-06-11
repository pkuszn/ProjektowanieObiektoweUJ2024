package edu.uj.po.simulation.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class CustomFileHandler {
    private final String path;
    private final File file;
    private LocalDateTime date;

    public CustomFileHandler(String path, LocalDateTime date) {
        super();
        this.path = path;
        file = new File(this.path);
        this.date = date;
    }

    public CustomFileHandler(String path) {
        super();
        this.path = path;
        file = new File(this.path);
    }

    public void write(String message, boolean append) throws IOException {
        if (file.exists())
        {
            saveToFile(message, append);
        }
        else {
            file.createNewFile();
            saveToFile(message, append);
        }  
    }

    public void writeAll(List<String> messages) throws Exception {
        if (file.exists()) {
            saveToFileAll(messages);
        } 
        else {
            file.createNewFile();
            saveToFileAll(messages);
        }
    }

    private void saveToFile(String message, boolean append) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, append))) {
            String str = this.date + "\t" + message;
            writer.write(str);
        }
    }

    private void saveToFileAll(List<String> messages) throws Exception {
        if (messages == null) {
            throw new NullPointerException();
        }
        if (messages.isEmpty()) {
            throw new Exception();
        }
        StringBuilder builder = new StringBuilder();
        
        for(String message : messages) {
            builder.append(message);
        }

        String result = builder.toString();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(result);
            writer.write("---");
        }
    }

}

package edu.uj.po.simulation.debugger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class FileHandler {
    private String path;
    private File file;
    private LocalDateTime date;

    public FileHandler(String path, LocalDateTime date) {
        super();
        this.path = path;
        file = new File(this.path);
        this.date = date;
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

    private void saveToFile(String message, boolean append) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, append))) {
            String str = this.date + "\t" + message;
            writer.write(str);
        }
    }
}
